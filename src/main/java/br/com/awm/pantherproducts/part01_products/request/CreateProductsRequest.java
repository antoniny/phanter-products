package br.com.awm.pantherproducts.part01_products.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "CreateProducts", description = "Atualizar produtos")
public class CreateProductsRequest implements Serializable {
    private static final long serialVersionUID = -4381727598097036364L;

    @Schema(description = "Código do Produto", required = true)
    @NotEmpty(message = "O campo 'id' é obrigatório e não pode ser vazio.")
    @Pattern(regexp = "^([-_0-9])*$", message = "O campo 'id' informado está em padrão incorreto.")
    private String id;

    @Schema(description = "Nome/Descrição do Produto", required = true)
    @NotEmpty(message = "O campo 'name' é obrigatório e não pode ser vazio.")
    private String name;

}
