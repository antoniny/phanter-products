package br.com.awm.pantherproducts.kafka.factory;


import br.com.awm.pantherproducts.kafka.enums.TopicResponseStatusType;
import br.com.awm.pantherproducts.kafka.response.TopicErrorResponse;
import br.com.awm.pantherproducts.kafka.response.TopicResponse;

public class TopicResponseFactory {

    private TopicResponseFactory() {
        // empty private constructor
    }

    public static <T> TopicResponse<T> buildSuccess(T response) {
        return TopicResponse.<T>builder()
                .status(TopicResponseStatusType.SUCCESS.toString())
                .response(response)
                .build();
    }

    public static <T> TopicResponse<T> buildSuccess() {
        return TopicResponse.<T>builder()
                .status(TopicResponseStatusType.SUCCESS.toString())
                .build();
    }

    public static <T> TopicResponse<T> buildError(String errorCode) {
        return TopicResponse.<T>builder()
                .status(TopicResponseStatusType.ERROR.toString())
                .error(TopicErrorResponse.builder().code(errorCode).build())
                .build();
    }

    public static <T> TopicResponse<T> buildError(String errorCode, String errorMessage) {
        return TopicResponse.<T>builder()
                .status(TopicResponseStatusType.ERROR.toString())
                .error(TopicErrorResponse.builder().code(errorCode).message(errorMessage).build())
                .build();
    }
}
