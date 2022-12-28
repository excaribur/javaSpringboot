package com.bob.backend.business;


import com.bob.backend.exception.BaseException;
import com.bob.backend.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductBusiness {

    public String getProductById(String id) throws BaseException {

        // TODO: get data from Database

        // if product not found
        if (Objects.equals("1234", id)) {
            throw ProductException.notFound();
        }

        return id;
    }
}
