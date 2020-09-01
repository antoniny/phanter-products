package br.com.awm.pantherproducts.part03_manager.service;

import br.com.awm.pantherproducts.part03_manager.Model.Product;
import br.com.awm.pantherproducts.part03_manager.Request.CreateProductRequest;
import br.com.awm.pantherproducts.part03_manager.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CreateProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;

    public void execute(List<CreateProductRequest> request) {

        request.forEach(
                createProductRequest -> repository.save(mapper.map(createProductRequest,Product.class))
        );

    }
}
