package com.tysystems.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tysystems.batch.plcust.PLCUSTItems;
import com.tysystems.project_management.dto.PL_CUSTVO;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration  {
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    public DataSource dataSource;

    @Autowired
    private PLCUSTItems pLCUSTItems;
    @Autowired
    private BatchJobListener listener;

    @Bean
    public Job importJob() {
        return jobBuilderFactory.get("importJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(stepPLCUST())
            .end()
            .build();
    }

    @Bean
    public Step stepPLCUST() {
        return stepBuilderFactory.get("stepPLCUST")
            .<PL_CUSTVO, PL_CUSTVO> chunk(10)
            .reader(pLCUSTItems.jsonFileReader())
            .processor(pLCUSTItems.processor())
            .writer(pLCUSTItems.writer())
            .build();
    }

}
