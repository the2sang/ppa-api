package io.spring.batch.configuration;

import java.util.*;

import javax.sql.DataSource;

import io.spring.batch.PPATableUpdateClassifier;
import io.spring.batch.domain.*;

import io.spring.batch.reader.TaxEmailBillInfoDataReader;
import io.spring.batch.writer.TbTaxBillInfoEncTableWriter;
import io.spring.batch.writer.preparedStatmementSetter.TbTaxBillInfoEncSetter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemWriter;
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

  @Bean
  public JdbcPagingItemReader<TaxEmailBillInfoVO> pagingTaxEmailBillInfoItemReader() {
    TaxEmailBillInfoDataReader dataReader = new TaxEmailBillInfoDataReader();
    dataReader.setDataSource(this.dataSource);

    return dataReader.getPagingReader();

  }

  @Bean
  JdbcBatchItemWriter<TaxEmailBillInfoVO> taxEmailBillInfoItemOutputWriter() {

    return new JdbcBatchItemWriterBuilder<TaxEmailBillInfoVO>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO TAX_EMAIL_BILL_INFO_OUTPUT (issue_id, issue_day, issue_dt) VALUES (:issueId, :issueDay, :issueDt)")
            .dataSource(this.dataSource)
            .build();
  }




  @Bean
  public CompositeItemProcessor compositeProcessor() {
    List<ItemProcessor> delegates = new ArrayList<>(2);
    delegates.add(processor1());
    delegates.add(processor2());

    CompositeItemProcessor processor = new CompositeItemProcessor<>();

    processor.setDelegates(delegates);

    return processor;
  }

  public ItemProcessor<TaxEmailBillInfoVO, String> processor1() {
    return TaxEmailBillInfoVO::getIssueId;
  }

  public ItemProcessor<String, String> processor2() {
    return name -> "안녕하세요. "+ name + "입니다.";
  }


  @Bean
  JdbcBatchItemWriter<TaxEmailBillInfoVO> tbTaxBillInfoEncWriter() {
    TbTaxBillInfoEncTableWriter writer = new TbTaxBillInfoEncTableWriter();
    JdbcBatchItemWriter<TaxEmailBillInfoVO> databaseItemWriter = new JdbcBatchItemWriter<>();
    databaseItemWriter.setDataSource(this.dataSource);

    databaseItemWriter.setSql(writer.getWriteSQL());

    ItemPreparedStatementSetter<TaxEmailBillInfoVO> valueSetter =
            new TbTaxBillInfoEncSetter();
    databaseItemWriter.setItemPreparedStatementSetter(valueSetter);

    return databaseItemWriter;
  }

  @Bean
  JdbcBatchItemWriter<TaxEmailBillInfoVO> taxEmailBillInfoEndingUpdate() {
    return new JdbcBatchItemWriterBuilder<TaxEmailBillInfoVO>()
            .dataSource(dataSource)
            .beanMapped()
            .sql("UPDATE TAX_EMAIL_BILL_INFO SET MAIL_STATUS_CODE = '01' WHERE ISSUE_ID = :issueId")
            .build();
  }

  @Bean
  ItemWriter<TaxEmailBillInfoVO> taxEmailBillInfoCustomWriter() {
    return items -> {
      for (TaxEmailBillInfoVO item : items) {
        System.out.println(item);
      }
    };
  }


  //PPA 배치처리를 위한 9개 테이블 Insert 및 Update
  @Bean
  public CompositeItemWriter<TaxEmailBillInfoVO> compositeItemWriter() {
    CompositeItemWriter<TaxEmailBillInfoVO> compositeItemWriter = new CompositeItemWriter<>();
    compositeItemWriter.setDelegates(Arrays.asList(tbTaxBillInfoEncWriter(), taxEmailBillInfoEndingUpdate()));

    return compositeItemWriter;
  }


  @Bean
  public Step step1()  {
    log.info("STEP-1 시작...");
    return stepBuilderFactory.get("step1")
      .<TaxEmailBillInfoVO, TaxEmailBillInfoVO>chunk(1)
      .reader(pagingTaxEmailBillInfoItemReader())
            //.processor(compositeProcessor())
      .writer(compositeItemWriter())
 //           .writer(taxEmailBillInfoEndingUpdate())
         //   .stream(tbTaxBillInfoEncWriter())
        //    .stream(taxEmailBillInfoEndingUpdate())
      .build();
  }

  @Bean
  public Step step2() {
    log.info("STEP-2 시작...");
    return stepBuilderFactory.get("step2")
            .<TaxEmailBillInfoVO, TaxEmailBillInfoVO>chunk(1)
            .reader(pagingTaxEmailBillInfoItemReader())
            .writer(taxEmailBillInfoEndingUpdate())
            .build();
  }



  @Bean
  public Job job() {
    return jobBuilderFactory.get("job")
            .preventRestart()
            .start(step1())
 //         .next(step2())
            .build();
  }
}