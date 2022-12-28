package com.bob.backend.api;

import com.bob.backend.business.TestBusiness;
import com.bob.backend.exception.BaseException;
import com.bob.backend.model.MRegisterRequest;
import com.bob.backend.model.TestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestApi {

    //  METHOD 1 :depencies injection @Autowire
//    @Autowired
//    private TestResponse business;

    // METHOD 2 : Constructor Injection this efficiency faster
    private final TestBusiness business;

    public TestApi(TestBusiness business) {
        this.business = business;
    }


    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setName("Dew");
        response.setFood("KFC");

        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody MRegisterRequest request) throws BaseException {


// THIS METHOD 1 TO ERROR HANDLE is replaced by throws BaseException
//        String response = null;
//        try {
//            response = business.register(request);
//            return ResponseEntity.ok(response);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
//        }
        String response = business.register(request);
        return ResponseEntity.ok(response);


    }

}
