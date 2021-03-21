package io.spring.batch.readSource;




import com.mchange.v2.c3p0.PooledDataSource;
import io.spring.batch.configuration.JobConfiguration;
import io.spring.batch.domain.*;
import io.spring.batch.reader.TaxEmailBillInfoDataReader;
import io.spring.batch.writer.EmailToTbTaxBillInfoEncInit;
import io.spring.batch.writer.TbTaxBillInfoEncTableWriter;
import io.spring.batch.writer.preparedStatmementSetter.TbTaxBillInfoEncSetter;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TaxEmailBillInfoItemReaderTest.BachConfiguration.class, BatchAutoConfiguration.class})
@SpringBatchTest
@EnableBatchProcessing
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TaxEmailBillInfoItemReaderTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

//    @Bean
//    public JobLauncherTestUtils jobLauncherTestUtils() {
//        return new JobLauncherTestUtils();
//    }

    @Test
    public void test() throws Exception {
        JobExecution jobExecution = this.jobLauncherTestUtils.launchJob();

//        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
//
//        StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
//
//        assertEquals(BatchStatus.COMPLETED, stepExecution.getStatus());

    }


//    @Before
//    public void setUp() {
//        this.context = new AnnotationConfigApplicationContext(TestDataSourceConfiguration.class); // (1)
//        this.dataSource = (DataSource) context.getBean("dataSource"); // (2)
//        this.jdbcTemplate = new JdbcTemplate(this.dataSource); // (3)
//        this.orderDate = LocalDate.of(2019, 10, 6);
//        this.job = new BatchOnlyJdbcReaderTestConfiguration(this.dataSource);
//        this.job.setChunkSize(100);
//    }

//    @After
//    public void tearDown() {
//        if (this.context != null) {
//            this.context.close();
//        }
//    }

//    @Test
//    public void TaxEmailBillInfo가져오기() throws Exception{
//        //JdbcPagingItemReader<TaxEmailBillInfoVO> reader = new JdbcPagingItemReader<>();
//
//    }

    @Configuration
    @EnableBatchProcessing
    public static class BachConfiguration {

        @Autowired
        private JobBuilderFactory jobBuilderFactory;

        @Bean
        public JobLauncherTestUtils jobLauncherTestUtils() {
            return new JobLauncherTestUtils();
        }

        @Autowired
        StepBuilderFactory stepBuilderFactory;

        @Autowired
        public DataSource dataSource;

        @Bean
        public JdbcPagingItemReader<TaxEmailBillInfoVO> pagingTaxEmailBillInfoItemReader() {

            TaxEmailBillInfoDataReader dataReader = new TaxEmailBillInfoDataReader();
            dataReader.setDataSource(this.dataSource);

            return dataReader.getPagingReader();

        }

        @Bean
        ItemWriter<TaxEmailBillInfoVO> tbTaxBillInfoEncItemWriter() {

            TbTaxBillInfoEncTableWriter writer = new TbTaxBillInfoEncTableWriter();

            JdbcBatchItemWriter<TaxEmailBillInfoVO> databaseItemWriter = new JdbcBatchItemWriter<>();
            databaseItemWriter.setDataSource(this.dataSource);
            //databaseItemWriter.setJdbcTemplate(jdbcTemplate);
            databaseItemWriter.setSql(writer.getWriteSQL());

            ItemPreparedStatementSetter<TaxEmailBillInfoVO> valueSetter =
                    new TbTaxBillInfoEncSetter(this.dataSource);
            databaseItemWriter.setItemPreparedStatementSetter(valueSetter);

            return databaseItemWriter;
        }


        @Bean
        ItemWriter<TaxEmailBillInfoVO> taxEmailBillInfoCustomWriter() {
            return items -> {
                for (TaxEmailBillInfoVO item : items) {
                    System.out.println(item);
                }
            };
        }

//        @Bean
//        public ItemProcessorAdapter<TaxEmailBillInfoVO, TbTaxBillInfoEncVO> itemProcessor(EmailToTbTaxBillInfoEncInit service) {
//
//            ItemProcessorAdapter<TaxEmailBillInfoVO, TbTaxBillInfoEncVO> adapter = new ItemProcessorAdapter<>();
//
//            adapter.setTargetObject(service);
//            adapter.setTargetMethod("getMakeUuid");
//
//            return adapter;
//
//        }




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
        public Step step1()  {
            return this.stepBuilderFactory.get("Step1")
                    .<TaxEmailBillInfoVO, TaxEmailBillInfoVO >chunk(10)
                    .reader(pagingTaxEmailBillInfoItemReader())
                    .processor(compositeProcessor())
                    .writer(taxEmailBillInfoCustomWriter())
                    .build();
        }

        @Bean
        public Job job() throws Exception {
            return this.jobBuilderFactory.get("job")
                .start(step1())
                    .build();
        }



        @Bean
        public DataSource dataSource() throws SQLException {

            //OracleDataSource dataSource = new OracleDataSource();
            OracleConnectionPoolDataSource dataSource = new OracleConnectionPoolDataSource();
            dataSource.setUser("exedi");
            dataSource.setPassword("exedi");
            dataSource.setURL("jdbc:oracle:thin:@//localhost:21152/xe");
            dataSource.setFastConnectionFailoverEnabled(true);
            dataSource.setImplicitCachingEnabled(true);
            return dataSource;
        }
}


}
