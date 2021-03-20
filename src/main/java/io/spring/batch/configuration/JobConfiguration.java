package io.spring.batch.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import io.spring.batch.domain.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created By K.H.C (Hanjun KDN)
 * @since 2021.03.10
 * @version 1.0
 */
@Configuration
@Slf4j
public class JobConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  public DataSource dataSource;

  public final int READER_FETCH_SIZE = 10;

  @Bean
  public JdbcPagingItemReader<CustomerInput> pagingItemReader() {
    JdbcPagingItemReader<CustomerInput> reader = new JdbcPagingItemReader<>();

    reader.setDataSource(this.dataSource);
    reader.setFetchSize(10);
    reader.setRowMapper(new CustomerRowMapper());

    //MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
    OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
    queryProvider.setSelectClause("ID, FIRSTNAME, LASTNAME");
    queryProvider.setFromClause("from CUSTOMER_INPUT");


    Map<String, Order> sortKeys = new HashMap<>(1);

    sortKeys.put("ID", Order.ASCENDING);

    queryProvider.setSortKeys(sortKeys);

    reader.setQueryProvider(queryProvider);

    return reader;
  }


  @Bean
  public JdbcPagingItemReader<TaxEmailBillInfoVO> pagingTaxSaleTaxInvoiceInfoItemReader() {
    JdbcPagingItemReader<TaxEmailBillInfoVO> reader = new JdbcPagingItemReader<>();

    reader.setDataSource(this.dataSource);
    reader.setFetchSize(READER_FETCH_SIZE);
    reader.setRowMapper(new TaxEmailBillInfoRowMapper());

    OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
    queryProvider.setSelectClause("*");
    queryProvider.setFromClause("from TAX_EMAIL_BILL_INFO");


    Map<String, Order> sortKeys = new HashMap<>(1);

    sortKeys.put("ID", Order.ASCENDING);

    queryProvider.setSortKeys(sortKeys);

    reader.setQueryProvider(queryProvider);

    return reader;

  }


  @Bean
  public JdbcBatchItemWriter<CustomerOutput> customerItemWriter() {
    JdbcBatchItemWriter<CustomerOutput> itemWriter = new JdbcBatchItemWriter<>();

    itemWriter.setDataSource(this.dataSource);
    itemWriter.setSql("INSERT INTO CUSTOMER_OUTPUT(FIRSTNAME,LASTNAME) VALUES (:firstName, :lastName)");
    itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
    itemWriter.afterPropertiesSet();
    return itemWriter;
  }

  @Bean
  public Step step1() {
    log.info("STEP-1 시작...");
    return stepBuilderFactory.get("step1")
      .<CustomerInput, CustomerOutput>chunk(10)
      .reader(pagingItemReader())
      .writer(customerItemWriter())
      .build();
  }

  @Bean
  public Step step2() {
    log.info("STEP-2 시작...");
    return stepBuilderFactory.get("step2")
            .<CustomerInput, CustomerOutput>chunk(10)
            .reader(pagingItemReader())
            .writer(customerItemWriter())
            .build();
  }

  @Bean
  public Job job() {
    return jobBuilderFactory.get("job")
            .start(step1())
            .on("FAILED")
            .end()
            .from(step1())
            .on("*")
            .to(step2())
            .on("FAILED")
            .end()
            .end()
            .build();
  }
}