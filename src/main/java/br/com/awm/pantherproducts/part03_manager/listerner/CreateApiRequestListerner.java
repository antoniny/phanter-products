package br.com.awm.pantherproducts.part03_manager.listerner;

import br.com.awm.pantherproducts.part03_manager.Request.ApiRequestRequest;
import br.com.awm.pantherproducts.part03_manager.service.CreateApiRequestService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static br.com.awm.pantherproducts.kafka.exceptions.KafkaListenerExceptionHandler.KAFKA_LISTENER_EXCEPTION_HANDLER;

@Service
public class CreateApiRequestListerner {

    @Autowired
    private CreateApiRequestService service;

    @KafkaListener(topics = "${spring.kafka.topic.create-api-request-product}", errorHandler = KAFKA_LISTENER_EXCEPTION_HANDLER)
    public void listenCreateApiRequest(String message) {

        ApiRequestRequest request = new Gson().fromJson(message, ApiRequestRequest.class);
        service.execute(request);
    }

}
