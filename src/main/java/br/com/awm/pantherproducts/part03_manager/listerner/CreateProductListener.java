package br.com.awm.pantherproducts.part03_manager.listerner;

import br.com.awm.pantherproducts.part03_manager.Request.CreateProductRequest;
import br.com.awm.pantherproducts.part03_manager.service.CreateProductService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.awm.pantherproducts.kafka.exceptions.KafkaListenerExceptionHandler.KAFKA_LISTENER_EXCEPTION_HANDLER;

@Service
public class CreateProductListener {


    @Autowired
    private CreateProductService service;

    @KafkaListener(topics = "${spring.kafka.topic.create-product}", errorHandler = KAFKA_LISTENER_EXCEPTION_HANDLER)
    public void listenCreateProduct(String message) {

        List<CreateProductRequest> request = new Gson().fromJson(message, new TypeToken<List<CreateProductRequest>>() {}.getType());
        service.execute(request);
    }

}
