package br.com.awm.pantherproducts.kafka.exceptions;


import br.com.awm.pantherproducts.kafka.enums.ErrorCode;

public class BadRequestException extends BaseException {

    public BadRequestException() {
        super(ErrorCode.BAD_REQUEST.getMessage(), ErrorCode.BAD_REQUEST.toString());
    }

    public BadRequestException(String message) {
        super(message, ErrorCode.BAD_REQUEST.toString());
    }
}
