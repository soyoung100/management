package com.tysystems.batch.plcust;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class PLCUSTStepExecutionListener implements StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(PLCUSTStepExecutionListener.class);
    
    
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("(Spring Batch) beforeStep");

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("(Spring Batch) afterStep");
        return null;
    }
}
