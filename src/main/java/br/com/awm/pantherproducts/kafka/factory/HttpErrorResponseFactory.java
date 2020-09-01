package br.com.awm.pantherproducts.kafka.factory;


import br.com.awm.pantherproducts.kafka.enums.ErrorCode;
import br.com.awm.pantherproducts.kafka.response.HttpErrorResponse;

import java.util.ArrayList;

public class HttpErrorResponseFactory {

    private HttpErrorResponseFactory() {
        throw new UnsupportedOperationException("Do not instantiate this class, use statically.");
    }

    public static HttpErrorResponse build(String errorCode, String message) {
        return HttpErrorResponse.builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }

    public static HttpErrorResponse build(ErrorCode errorCode) {
        return HttpErrorResponse.builder()
                .errorCode(errorCode.toString())
                .message(errorCode.getMessage())
                .errors(new ArrayList<>())
                .build();
    }
}
