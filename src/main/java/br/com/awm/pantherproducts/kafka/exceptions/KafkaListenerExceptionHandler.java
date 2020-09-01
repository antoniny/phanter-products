package br.com.awm.pantherproducts.kafka.exceptions;


import br.com.awm.pantherproducts.kafka.enums.ErrorCode;
import br.com.awm.pantherproducts.kafka.factory.TopicResponseFactory;
import com.google.gson.JsonParseException;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.naming.ServiceUnavailableException;
import java.util.Objects;

@Component(KafkaListenerExceptionHandler.KAFKA_LISTENER_EXCEPTION_HANDLER)
public class KafkaListenerExceptionHandler implements KafkaListenerErrorHandler {

    public static final String KAFKA_LISTENER_EXCEPTION_HANDLER = "KAFKA_LISTENER_EXCEPTION_HANDLER";

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {

        if (Objects.isNull(e.getCause()))
            return TopicResponseFactory.buildError(ErrorCode.UNEXPECTED_ERROR.toString(), ErrorCode.UNEXPECTED_ERROR.getMessage()).toJson();

        if (e.getCause() instanceof BadRequestException) {
            BadRequestException badRequestException = (BadRequestException) e.getCause();
            return TopicResponseFactory.buildError(ErrorCode.BAD_REQUEST.toString(), badRequestException.getMessage()).toJson();
        }

        if (e.getCause() instanceof JsonParseException) {
            return TopicResponseFactory.buildError(ErrorCode.BAD_REQUEST.toString(), message.getPayload().toString()).toJson();
        }

        if (e.getCause() instanceof ServiceUnavailableException) {
            return TopicResponseFactory.buildError(ErrorCode.SERVICE_UNAVAILABLE.toString(), ErrorCode.SERVICE_UNAVAILABLE.getMessage()).toJson();
        }

        if (e.getCause() instanceof BaseException) {
            BaseException baseException = (BaseException) e.getCause();
            return TopicResponseFactory.buildError(baseException.getErrorCode(), baseException.getMessage()).toJson();
        }
        
        return TopicResponseFactory.buildError(ErrorCode.UNEXPECTED_ERROR.toString(), e.getMessage()).toJson();

    }

}
