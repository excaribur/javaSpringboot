package com.bob.backend.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

public class ProductException extends BaseException {

    public ProductException(String code) {
        super("product" + code);
    }

    public static ProductException notFound() {
        return new ProductException("not.found");
    }
}
