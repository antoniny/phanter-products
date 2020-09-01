package br.com.awm.pantherproducts.part01_products.exceptions.handle;


import br.com.awm.pantherproducts.part01_products.enums.ErrorCode;

import java.util.ArrayList;

public class HttpErrorResponseFactory {

    private HttpErrorResponseFactory() {
    }

    public static HttpErrorResponse build(String errorCode, String message) {
        return HttpErrorResponse.builder().errorCode(errorCode).message(message).build();
    }

    public static HttpErrorResponse build(ErrorCode errorType) {
        return HttpErrorResponse.builder()
                .errorCode(errorType.toString())
                .message(errorType.getMessage())
                .errors(new ArrayList<>())
                .build();
    }
}
