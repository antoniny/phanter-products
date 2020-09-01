package br.com.awm.pantherproducts.part01_products.exceptions;

import br.com.awm.pantherproducts.part01_products.enums.ErrorCode;

public class UnableToGetKeyListProductException extends BaseException {

    public UnableToGetKeyListProductException() {
        super(ErrorCode.UNABLE_TO_GET_KEY_LIST_PRODUCT.getMessage(), ErrorCode.UNABLE_TO_GET_KEY_LIST_PRODUCT.toString());
    }
}
