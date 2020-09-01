package br.com.awm.pantherproducts.part01_products.resource;

import br.com.awm.pantherproducts.kafka.response.HttpErrorResponse;
import br.com.awm.pantherproducts.part01_products.request.CreateProductsRequest;
import br.com.awm.pantherproducts.part01_products.service.CreateProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController("Products")
@ApiResponse(responseCode = "403", description = "FORBIDDEN", content = @Content(schema = @Schema(implementation = HttpErrorResponse.class)))
@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = HttpErrorResponse.class)))
@Tag(name = "Produtos", description = "API de cadastro de Produtos")
public class ProductsResource {

    @Autowired
    CreateProductsService createProductsService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualização Cadastral.", description = "Atualizar dados cadastrais do produto.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity postProducts(
            @Valid
            @RequestBody
            @NotEmpty(message = "A lista de produtos não pode ser vazia.")
            List<@Valid CreateProductsRequest> request ){

        createProductsService.execute(request);
        return ResponseEntity.ok().build();
    }

}
