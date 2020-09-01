package br.com.awm.pantherproducts.part03_manager.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest implements Serializable {

    private static final long serialVersionUID = -7641107507727805504L;

    private String id;
    private String name;
}
