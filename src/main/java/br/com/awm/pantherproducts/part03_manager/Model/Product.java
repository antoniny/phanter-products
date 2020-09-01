package br.com.awm.pantherproducts.part03_manager.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@TypeAlias("products")
public class Product {

    @NotNull
    private String id;
    @NotBlank(message = "Campo 'name' não pode ser nulo ou vazio.")
    private String name;

}
