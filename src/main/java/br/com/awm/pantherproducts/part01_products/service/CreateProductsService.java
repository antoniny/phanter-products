package br.com.awm.pantherproducts.part01_products.service;

import br.com.awm.pantherproducts.kafka.KafkaRequestSender;
import br.com.awm.pantherproducts.part01_products.request.ApiRequestRequest;
import br.com.awm.pantherproducts.part01_products.request.CreateProductsRequest;
import br.com.awm.pantherproducts.part01_products.utils.CheckSumMD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CreateProductsService extends KafkaRequestSender<List<CreateProductsRequest>> {

    @Value("${spring.kafka.topic.create-product}")
    private String createApiRequestProduct;

    @Autowired
    private ValidateLastExecuteApiRequestService validateLastExecuteApiRequestService;

    @Autowired
    private CreateApiRequestService createApiRequestService;

    public void execute(List<CreateProductsRequest> request) {

        String key = CheckSumMD5Utils.getChecksum(request);
        ApiRequestRequest apiRequest = ApiRequestRequest.builder().key(key).build();
        validateLastExecuteApiRequestService.execute(apiRequest);
        createApiRequestService.execute(apiRequest);
        sendCreateProduct(request.stream().distinct().collect(Collectors.toList()));
    }



    public void sendCreateProduct(List<CreateProductsRequest> request){
        send(createApiRequestProduct, request);
    }
}
