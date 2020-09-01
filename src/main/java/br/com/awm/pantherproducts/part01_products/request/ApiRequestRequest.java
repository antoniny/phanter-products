package br.com.awm.pantherproducts.part01_products.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequestRequest implements Serializable {
    private static final long serialVersionUID = 3889472671691413480L;

    private String key;
}
