package com.suwi.logging.logback;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import java.util.*;

public class SpringLoggingHelper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void helpMethod(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.info(MarkerFactory.getMarker("NOTIFY_APP_SUPPORT"),"This is an info message with NOTIFY_APP_SUPPORT");
        logger.warn(MarkerFactory.getMarker("NOTIFY_APP_SUPPORT"),"This is an info message with NOTIFY_APP_SUPPORT");
        logger.error(MarkerFactory.getMarker("NOTIFY_APP_SUPPORT"),"This is an info message with NOTIFY_APP_SUPPORT");
        logger.debug(MarkerFactory.getMarker("NOTIFY_APP_SUPPORT"),"This is an info message with NOTIFY_APP_SUPPORT");
        logger.error("Error Message Logged !!!", new LogException("Something is NULL"));
        logger.info(MarkerFactory.getMarker("NOTIFY_APP_SUPPORT"),"This is an info message with NOTIFY_APP_SUPPORT");


        logger.info(MarkerFactory.getMarker("STATUS"),"This is an info message with STATUS");


        Map<String, String> customer = new LinkedHashMap<String, String>();
        customer.put("id", "12345");
        customer.put("ssn", "777-45-6789");
        customer.put("oldssn", "777-45-6789");
        customer.put("tin", "856-45-6789");
        customer.put("BusinessTin", "345-45-6789");
        customer.put("email", "admin@email.com");
        customer.put("zzjsonData", ""+new JSONObject(customer));
        System.out.println(PIIUtil.maskJsonPIISensitiveInfo (""+new JSONObject(customer)));

        logger.info(MarkerFactory.getMarker("NOTIFY_APP_SUPPORT"),"Customer found : {}", new JSONObject(customer));
    }


}
