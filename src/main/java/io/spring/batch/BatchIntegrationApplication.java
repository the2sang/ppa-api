package io.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created By K.H.C (Hanjun KDN)
 * @since 2021.03.10
 * @version 1.0
 */
@SpringBootApplication
@EnableBatchProcessing(modular = false)
@EntityScan("io.spring.batch.domain")
public class BatchIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchIntegrationApplication.class, args);
    }

}
