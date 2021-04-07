package com.wish.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component("stepExecListner")
public class StepExecListner implements StepExecutionListener {
    Logger logger = LoggerFactory.getLogger(StepExecListner.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("SPStepListener - CALLED BEFORE STEP.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("SPStepListener - CALLED AFTER STEP.");
        return ExitStatus.COMPLETED;
    }
}
