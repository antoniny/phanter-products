package br.com.awm.pantherproducts.kafka.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseException extends RuntimeException {

    protected final String errorCode;

    public BaseException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;

        log.error("errorCode", errorCode);
        log.error("errorMessage", msg);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
