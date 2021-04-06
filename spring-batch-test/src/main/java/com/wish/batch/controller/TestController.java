package com.wish.batch.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("importUserJob")
    Job job;

    @GetMapping("/job1")
    public BatchStatus test() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time",new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(maps);

        JobExecution jobExecution= jobLauncher.run(job,jobParameters);

        System.out.println("Status : " + jobExecution.getStatus());
        while(jobExecution.isRunning()){
            System.out.print("...");
        }

        return jobExecution.getStatus();
    }
}
