package br.com.awm.pantherproducts.part03_manager.service;

import br.com.awm.pantherproducts.part03_manager.Model.ApiRequest;
import br.com.awm.pantherproducts.part03_manager.Request.ApiRequestRequest;
import br.com.awm.pantherproducts.part03_manager.Response.ApiRequestResponse;
import br.com.awm.pantherproducts.part03_manager.repository.ApiRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CreateApiRequestService {

    @Autowired
    private ApiRequestRepository repository;

    @Autowired
    private GetApiRequestService getApiRequestService;

    @Autowired
    private ModelMapper mapper;

    public ApiRequestResponse execute(ApiRequestRequest request){

        return mapper.map(
                repository.findBykeyRequest(request.getKey())
                        .map(apiFound -> {
                            apiFound.setLastUpdate(LocalDateTime.now());
                            return repository.save(apiFound);
                        }).orElseGet(() -> {
                            ApiRequest response = mapper.map(getApiRequestService.execute(request), ApiRequest.class);
                            response.setLastUpdate(LocalDateTime.now());
                            return repository.save(response);
                        })
                , ApiRequestResponse.class
        );
    }
}
