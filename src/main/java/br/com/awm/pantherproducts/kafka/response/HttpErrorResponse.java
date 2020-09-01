package br.com.awm.pantherproducts.kafka.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HttpErrorResponse {

    @Schema(description = "Código do erro")
    private String errorCode;

    @Schema(description = "Descrição do erro")
    private String message;

    @Schema(description = "Campo e erro ocorrido", example = "name: O campo nome é obrigatório")
    private List<String> errors;
    
}