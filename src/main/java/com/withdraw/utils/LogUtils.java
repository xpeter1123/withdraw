package com.withdraw.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class LogUtils {
    public static void logRestApiStart(Logger logger) {
        logger.info(getRequestInfo());
    }

    public static void logRestApiRequestBody(Logger logger, Object requestBody) {
        logger.info(getRequestInfo() + " requestBody: {}", requestBody);

    }

    public static String getRequestInfo() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            return new StringBuilder()
                    .append(request.getMethod())
                    .append(StringUtils.SPACE)
                    .append(request.getRequestURI())
                    .append(StringUtils.SPACE)
                    .append(request.getQueryString())
                    .toString();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static void logRestApiException(Logger logger, Exception exception) {
        logger.error(getRequestInfo() + " {}", getLogStackTrace(exception));
    }

    public static void logRestApiError(Logger logger, String msg, Object... params) {
        logger.error(getRequestInfo() + StringUtils.SPACE + msg, params);
    }

    public static String getLogStackTrace(Exception exception) {
        return new StringBuilder()
                .append("Cause: ")
                .append(exception.getCause())
                .append(StringUtils.LF)
                .append("Root cause: ")
                .append(ExceptionUtils.getRootCauseMessage(exception))
                .append(StringUtils.LF)
                .append(ExceptionUtils.getStackTrace(exception))
                .toString();
    }

    public static void logRestApiResponseBody(Logger logger, Object responseBody) {
        logger.debug(getRequestInfoString() + " responseBody: {}", responseBody);
    }

    public static String getRequestInfoString() {
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            return new StringBuilder()
                    .append(request.getMethod())
                    .append(StringUtils.SPACE)
                    .append(request.getRequestURI())
                    .toString();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }
}
