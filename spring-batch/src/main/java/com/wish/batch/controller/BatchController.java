package com.wish.batch.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("employeeMigrationJob")
    Job employeeMigrationJob;

    @GetMapping("/test")
    public ResponseEntity test() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("time",new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(parameters);

        JobExecution jobExecution= jobLauncher.run(employeeMigrationJob,jobParameters);

        while(jobExecution.isRunning()){
            System.out.print("=");
        }

        return ResponseEntity.ok(jobExecution.getStatus());
    }
}
