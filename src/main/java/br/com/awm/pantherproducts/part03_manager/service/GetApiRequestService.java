package br.com.awm.pantherproducts.part03_manager.service;

import br.com.awm.pantherproducts.part03_manager.Model.ApiRequest;
import br.com.awm.pantherproducts.part03_manager.Request.ApiRequestRequest;
import br.com.awm.pantherproducts.part03_manager.Response.ApiRequestResponse;
import br.com.awm.pantherproducts.part03_manager.repository.ApiRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service("GetKeyRequestListProductsService3")
public class GetApiRequestService {

    @Autowired
    private ApiRequestRepository repository;

    @Autowired
    private ModelMapper mapper;

    public ApiRequestResponse execute(ApiRequestRequest request) {

        Optional<ApiRequest> apiRequest = repository.findBykeyRequest(request.getKey());

        return apiRequest
                .map(api ->  mapper.map(api , ApiRequestResponse.class))
                .orElseGet(() ->  mapper.map(ApiRequest.builder().keyRequest(request.getKey()).lastUpdate(LocalDateTime.now().minusYears(1L)).build(),ApiRequestResponse.class));

    }

}
