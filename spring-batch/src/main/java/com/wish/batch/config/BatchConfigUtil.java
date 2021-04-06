package com.wish.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfigUtil{
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;


    public Job registerJob(String jobName,BatchStep... batchSteps){

        if(batchSteps.length==1){
            System.out.println("**************** registerJob " +jobName);
            System.out.println("**************** registerJob " + jobBuilderFactory);
            return jobBuilderFactory
                    .get(jobName)
                    .incrementer(new RunIdIncrementer())
                    .start(createStep(batchSteps[0]))
                    .build();

        }
        if(batchSteps.length>1){


            FlowJobBuilder flowJobBuilder = jobBuilderFactory
                    .get(jobName)
                    .incrementer(new RunIdIncrementer())
                    .flow(createStep(batchSteps[0]))
                                .build();
            for(int i=1;i<batchSteps.length;i++){

                //flowJobBuilder = flowJobBuilder.flow(createStep(batchSteps[0]));
            }
            return flowJobBuilder.build();

        }
        return null;

    }

    private Step createStep(BatchStep batchStep) {

        StepBuilder stepBuilder = stepBuilderFactory.get(batchStep.getName());
        SimpleStepBuilder simpleStepBuilder = stepBuilder.chunk(100);

        if(batchStep.getChunkSize()>0){
            simpleStepBuilder = stepBuilder.chunk(batchStep.getChunkSize());
        }
        if(batchStep.getReader()!=null){
            simpleStepBuilder = simpleStepBuilder.reader(batchStep.getReader());
        }
        if(batchStep.getProcessor()!=null){
            simpleStepBuilder = simpleStepBuilder.processor(batchStep.getProcessor());
        }

        if(batchStep.getWriter()!=null){
            simpleStepBuilder = simpleStepBuilder.writer(batchStep.getWriter());
        }

        return simpleStepBuilder.build();
    }
}
