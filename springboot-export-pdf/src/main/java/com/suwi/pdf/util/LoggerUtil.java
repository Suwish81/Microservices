package com.suwi.pdf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LoggerUtil {
    static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    public static void debug(String msg){
        logger.debug(MarkerFactory.getMarker("NOTIFY_ADMIN"), msg);

    }

    public static void main(String[] args) {
        LoggerUtil.debug("sadas");
    }
}
