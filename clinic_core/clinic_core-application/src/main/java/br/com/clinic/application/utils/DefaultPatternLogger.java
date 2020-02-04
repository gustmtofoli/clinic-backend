package br.com.clinic.application.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class DefaultPatternLogger {
    private static final String LOG_FORMAT = "%s";
    private Logger logger;
    private boolean debugEnabled = false;


    public static DefaultPatternLogger getLogger(Class clazz) {
        return new DefaultPatternLogger(clazz);
    }

    private DefaultPatternLogger(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public void info(String message) {
        if (logger.isInfoEnabled()) {
            logger.info(addStaticInformation(message));
        }
    }

    public void info(Throwable throwable) { info(throwable.getMessage()); }

    public void debug(String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(addStaticInformation(message));
        }
    }

    public void debug(String message, Throwable throwable) {
        if (logger.isDebugEnabled()) {
            logger.debug(addStaticInformation(message), throwable);
        }
    }

    public void warn(String message) {
        if (logger.isWarnEnabled()) {
            logger.debug(addStaticInformation(message));
        }
    }

    public void warn(String message, Throwable throwable) {
        if (logger.isWarnEnabled()) {
            logger.debug(addStaticInformation(message), throwable);
        }
    }

    public void error(String message) {
        if (logger.isErrorEnabled()) {
            logger.debug(addStaticInformation(message));
        }
    }

    public void error(String message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.debug(addStaticInformation(message), throwable);
        }
    }

    public void error(Throwable throwable) {
        error(throwable.getMessage());
    }

    private String addStaticInformation(String message) {
        return format(LOG_FORMAT, message);
    }

    public boolean isDebugEnabled() {
        return debugEnabled;
    }
}
