package br.com.awm.pantherproducts.part01_products.exceptions.handle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModalTypeResponse implements Serializable {

    private static final long serialVersionUID = -4664721974264270377L;

    private String id;

    private String code;

    private String name;

    private String sellerId;
}
