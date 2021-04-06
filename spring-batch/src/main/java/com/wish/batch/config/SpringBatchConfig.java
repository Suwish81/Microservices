package com.wish.batch.config;

import com.wish.batch.data.oracle.entity.Employee;
import com.wish.batch.data.oracle.repository.EmployeeRepository;
import com.wish.batch.processor.EmployeeItemProcessor;
import com.wish.batch.reader.EmployeeOracleReader;
import com.wish.batch.writer.EmployeeMysqlWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

@Configuration
public class SpringBatchConfig {

    @Autowired
    BatchConfigUtil batchConfigUtil;

    @Autowired
    EmployeeOracleReader employeeOracleReader;

    @Autowired
    EmployeeItemProcessor employeeItemProcessor;

    @Autowired
    EmployeeMysqlWriter employeeMysqlWriter;



    @Bean(name = "employeeMigrationJob")
    @DependsOn({"employeeOracleReader","employeeItemProcessor","employeeMysqlWriter"})
    public Job myFirstJob(){
        System.out.println("**************** Creating Jobs");

        BatchStep step = new BatchStep();
        step.setName("Job1-Step1");
        step.setChunkSize(100);
        step.setReader(employeeOracleReader);
        step.setProcessor(employeeItemProcessor);
        step.setWriter(employeeMysqlWriter);

        return batchConfigUtil.registerJob("My-FirstJob",step);
       }




}
