package br.com.awm.pantherproducts.part01_products.service;

import br.com.awm.pantherproducts.kafka.KafkaRequestReplySender;
import br.com.awm.pantherproducts.part01_products.exceptions.ExecutionNotAuthorizedException;
import br.com.awm.pantherproducts.part01_products.request.ApiRequestRequest;
import br.com.awm.pantherproducts.part01_products.response.ApiRequestResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidateLastExecuteApiRequestService {

    @Value("${app.request.limit.interval.minutes}")
    private long intervalMinutesLimit;

    @Autowired
    private GetApiRequestService getApiRequestService;

    public void execute(ApiRequestRequest request){

        ApiRequestResponse response = getApiRequestService.execute(request);
        validateLastExecution(response);
    }

    private void validateLastExecution(ApiRequestResponse lastExecution) {

        if (LocalDateTime.now()
                .minusMinutes(intervalMinutesLimit)
                .compareTo(lastExecution.getLastUpdate() ) < 0L ){
            throw new ExecutionNotAuthorizedException();
        }
    }



}
