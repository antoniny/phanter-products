package br.com.awm.pantherproducts.part03_manager.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequestResponse implements Serializable {
    private static final long serialVersionUID = -5383511652909184044L;

    private String key;
    private LocalDateTime lastUpdate;

}
