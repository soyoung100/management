package com.tysystems.batch.support;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchService {

    @Autowired
    private ObjectProvider<JobRepository> jobRepositoryProvider;
    
    public boolean isBatchRunning() {
        if (jobRepositoryProvider.getObject() != null)
            return false;
        else
            return true;
    }
}
