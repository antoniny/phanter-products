package br.com.awm.pantherproducts.kafka.response;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicErrorResponse {
    @Expose
    private String code;
    @Expose
    private String message;
}
