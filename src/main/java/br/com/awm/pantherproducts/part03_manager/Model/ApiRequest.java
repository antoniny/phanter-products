package br.com.awm.pantherproducts.part03_manager.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "api_request")
@TypeAlias("api_request")
public class ApiRequest {


    @Id
    private String id;

    @NotNull
    private String keyRequest;

    @Builder.Default
    private LocalDateTime lastUpdate = LocalDateTime.now();


}
