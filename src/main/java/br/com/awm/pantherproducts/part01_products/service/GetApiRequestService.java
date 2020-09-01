package br.com.awm.pantherproducts.part01_products.service;

import br.com.awm.pantherproducts.kafka.KafkaRequestReplySender;
import br.com.awm.pantherproducts.kafka.response.TopicResponse;
import br.com.awm.pantherproducts.part01_products.exceptions.UnableToGetKeyListProductException;
import br.com.awm.pantherproducts.part01_products.request.ApiRequestRequest;
import br.com.awm.pantherproducts.part01_products.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GetApiRequestService {

    @Value("${spring.kafka.topic.request-api-request-product}")
    private String requestKeyRequestProductTopic;

    @Value("${spring.kafka.topic.reply-api-request-product}")
    private String replyKeyRequestProductTopic;

    @Autowired
    private KafkaRequestReplySender kafkaRequestReplySender;

    public ApiRequestResponse execute(ApiRequestRequest request) {
        try {
            String reply = kafkaRequestReplySender.send(requestKeyRequestProductTopic, replyKeyRequestProductTopic, request);
            return TopicResponse.fromJson(reply, ApiRequestResponse.class).getResponse();
        } catch (Exception e) {
            throw new UnableToGetKeyListProductException();
        }
    }
}
