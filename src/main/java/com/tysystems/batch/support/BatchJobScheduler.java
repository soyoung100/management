package com.tysystems.batch.support;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tysystems.batch.BatchConfiguration;
import com.tysystems.batch.BatchJobListener;

@Component
public class BatchJobScheduler {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    BatchConfiguration batchConfiguration;
    @Autowired
    BatchJobListener listener;

    private static final Logger log = LoggerFactory.getLogger(BatchJobScheduler.class);

    @Scheduled(cron = "0 50 15 * * *")
    public void runJob() {

        String todayDataFilePath = "./filestorage/plcust/plcust_" + LocalDate.now() + ".json";
        ClassPathResource file = new ClassPathResource(todayDataFilePath);
        if (!file.exists()) {

            // 오늘 날짜의 파일이 없을 경우, Spring Batch를 실행시키지 않습니다.
            log.info("(Spring Scheduler for Batch) There isn't TODAY file. Didn't Start PLCUST Job.");

        } else {

            Map<String, JobParameter> confMap = new HashMap<>();
            confMap.put("time", new JobParameter(System.currentTimeMillis()));
            JobParameters jobParameters = new JobParameters(confMap);
    
            try {
    
                log.info("(Spring Scheduler for Batch) Job Scheduler started.");
    
                jobLauncher.run(batchConfiguration.importJob(), jobParameters);
    
            } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                    | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
    
                log.error(e.getMessage());
            }
            
        }
        
        
    }
}
