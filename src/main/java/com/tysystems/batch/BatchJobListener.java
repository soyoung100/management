package com.tysystems.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BatchJobListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(BatchJobListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BatchJobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

        long num = jdbcTemplate.queryForObject("select count(*) from pl_cust", Long.class);
        log.info("(Spring Batch) JOB STARTED. There are " + num + " rows in table 'pl_cust'.");
        
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            long num = jdbcTemplate.queryForObject("select count(*) from pl_cust", Long.class);
            log.info("(Spring Batch) JOB FINISHED. There are " + num + " rows in table 'pl_cust'.");
        }

    }
}