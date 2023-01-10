package com.bob.backend.api;

import com.bob.backend.business.UserBusiness;
import com.bob.backend.exception.BaseException;
import com.bob.backend.model.MLoginRequest;
import com.bob.backend.model.MRegisterRequest;
import com.bob.backend.model.MRegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

    //  METHOD 1 :depencies injection @Autowire
//    @Autowired
//    private TestResponse business;

    // METHOD 2 : Constructor Injection this efficiency faster
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseException {

        String response = business.login(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {

        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException {
        String response = business.refreshToken();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload-profile")
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException{
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}

