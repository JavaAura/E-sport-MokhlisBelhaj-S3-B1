package com.Esport.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Esport.Repository.Impl.EquipeRepositoryImpl;

public class LoggerUtil {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public LoggerUtil(Class<EquipeRepositoryImpl> class1) {
        
	}

	public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
