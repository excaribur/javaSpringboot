package com.bob.backend.business;

import com.bob.backend.exception.BaseException;
import com.bob.backend.exception.UserException;
import com.bob.backend.model.MRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TestBusiness {

    public String register(MRegisterRequest request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }

        // validate email
        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }

        // validate..

        return "";
    }
}
