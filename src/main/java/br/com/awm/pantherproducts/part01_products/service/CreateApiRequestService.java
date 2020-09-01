package br.com.awm.pantherproducts.part01_products.service;

import br.com.awm.pantherproducts.kafka.KafkaRequestSender;
import br.com.awm.pantherproducts.part01_products.request.ApiRequestRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("CreateApiRequestService_Products")
public class CreateApiRequestService extends KafkaRequestSender<ApiRequestRequest> {

    @Value("${spring.kafka.topic.create-api-request-product}")
    private String createApiRequestProduct;


    public void execute(ApiRequestRequest request){

        send(createApiRequestProduct, request);
    }

}
