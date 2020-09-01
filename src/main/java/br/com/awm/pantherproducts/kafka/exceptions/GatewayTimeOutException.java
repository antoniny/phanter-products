package br.com.awm.pantherproducts.kafka.exceptions;


import br.com.awm.pantherproducts.kafka.enums.ErrorCode;

public class GatewayTimeOutException extends BaseException {

    public GatewayTimeOutException() {
        super(ErrorCode.GATEWAY_TIMEOUT.getMessage(), ErrorCode.GATEWAY_TIMEOUT.toString());
    }

    public GatewayTimeOutException(String message) {
        super(message, ErrorCode.GATEWAY_TIMEOUT.toString());
    }
}
