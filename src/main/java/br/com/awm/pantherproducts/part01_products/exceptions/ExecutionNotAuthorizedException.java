package br.com.awm.pantherproducts.part01_products.exceptions;

import br.com.awm.pantherproducts.part01_products.enums.ErrorCode;

public class ExecutionNotAuthorizedException extends BaseException {

    public ExecutionNotAuthorizedException() {
        super(ErrorCode.EXECUTION_NOT_AUTHORIZED.getMessage(), ErrorCode.EXECUTION_NOT_AUTHORIZED.toString());
    }
}