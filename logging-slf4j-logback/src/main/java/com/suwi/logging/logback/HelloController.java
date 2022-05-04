package com.suwi.logging.logback;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/test")
    public String hello() {
        System.out.println(logger.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        new SpringLoggingHelper().helpMethod();

        logger.info(MarkerFactory.getMarker("NOTIFY_APP_SUPPORT"),"This is an info message with NOTIFY_APP_SUPPORT");
        logger.info(MarkerFactory.getMarker("STATUS"),"This is an info message with STATUS");



        return "indexd"; // index.html
    }

}