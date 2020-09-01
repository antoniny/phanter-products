package br.com.awm.pantherproducts.part03_manager.repository;

import br.com.awm.pantherproducts.part03_manager.Model.ApiRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiRequestRepository extends MongoRepository<ApiRequest, String> {

    Optional<ApiRequest> findBykeyRequest(String key);

}
