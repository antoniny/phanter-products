package br.com.awm.pantherproducts.part03_manager.listerner;

import br.com.awm.pantherproducts.kafka.factory.TopicResponseFactory;
import br.com.awm.pantherproducts.part03_manager.Request.ApiRequestRequest;
import br.com.awm.pantherproducts.part03_manager.Response.ApiRequestResponse;
import br.com.awm.pantherproducts.part03_manager.service.GetApiRequestService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import static br.com.awm.pantherproducts.kafka.exceptions.KafkaListenerExceptionHandler.KAFKA_LISTENER_EXCEPTION_HANDLER;

@Service
public class GetApiRequestListener {

    @Autowired
    private GetApiRequestService service;

    @SendTo()
    @KafkaListener(topics = "${spring.kafka.topic.request-api-request-product}", errorHandler = KAFKA_LISTENER_EXCEPTION_HANDLER)
    public String listenAndReply(String message) {
        ApiRequestRequest request = new Gson().fromJson(message, ApiRequestRequest.class);
        ApiRequestResponse response = service.execute(request);
        return TopicResponseFactory.buildSuccess(response).toJson();
    }

}
