package br.com.awm.pantherproducts.kafka.exceptions;

import br.com.awm.pantherproducts.kafka.enums.ErrorCode;

public class KafkaTimedOutException extends BaseException {
    public KafkaTimedOutException(String msg) {
        super(msg, ErrorCode.KAFKA_TIMED_OUT.toString());
    }
}
