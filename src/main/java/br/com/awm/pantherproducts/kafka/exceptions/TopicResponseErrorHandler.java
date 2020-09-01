package br.com.awm.pantherproducts.kafka.exceptions;


import br.com.awm.pantherproducts.kafka.enums.ErrorCode;
import br.com.awm.pantherproducts.kafka.response.TopicErrorResponse;
import br.com.awm.pantherproducts.kafka.response.TopicResponse;
import org.springframework.stereotype.Component;


@Component
public class TopicResponseErrorHandler {

    private static final String SUCCESS_STATUS = "SUCCESS";

    public void handleExceptions(String message) {
        TopicResponse<TopicErrorResponse> topicResponse = TopicResponse.fromJson(message, TopicErrorResponse.class);

        if(SUCCESS_STATUS.equalsIgnoreCase(topicResponse.getStatus()))
            return;

        String errorCode = topicResponse.getError().getCode();
        String errorMessage = topicResponse.getError().getMessage();
        ErrorCode code;

        try {
            code = ErrorCode.valueOf(errorCode);
        } catch (IllegalArgumentException ex){
            throw new UnexpectedErrorException(errorMessage);
        }

        switch (code){
            case UNEXPECTED_ERROR:
                throw  new UnexpectedErrorException(errorMessage);
            case SERVICE_UNAVAILABLE:
                throw new ServiceUnavailableException(errorMessage);
            case BAD_REQUEST:
                throw new BadRequestException(errorMessage);
            case GATEWAY_TIMEOUT:
                throw new GatewayTimeOutException(errorMessage);
        }
    }


}
