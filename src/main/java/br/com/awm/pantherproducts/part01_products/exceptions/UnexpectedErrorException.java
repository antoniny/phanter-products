package br.com.awm.pantherproducts.part01_products.exceptions;


import br.com.awm.pantherproducts.part01_products.enums.ErrorCode;

public class UnexpectedErrorException extends BaseException {
    public UnexpectedErrorException() {
        super(ErrorCode.UNEXPECTED_ERROR.getMessage(), ErrorCode.UNEXPECTED_ERROR.toString());
    }

    public UnexpectedErrorException(String message) {
        super(message, ErrorCode.UNEXPECTED_ERROR.toString());
    }
}
