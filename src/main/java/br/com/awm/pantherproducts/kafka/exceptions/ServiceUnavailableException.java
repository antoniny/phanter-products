package br.com.awm.pantherproducts.kafka.exceptions;


import br.com.awm.pantherproducts.kafka.enums.ErrorCode;

public class ServiceUnavailableException extends BaseException{

    public ServiceUnavailableException() {
        super(ErrorCode.SERVICE_UNAVAILABLE.getMessage(), ErrorCode.SERVICE_UNAVAILABLE.toString());
    }

    public ServiceUnavailableException(String message) {
        super(message, ErrorCode.SERVICE_UNAVAILABLE.toString());
    }
}
