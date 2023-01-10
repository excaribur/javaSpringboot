package com.bob.backend.business;

import com.bob.backend.entity.User;
import com.bob.backend.exception.BaseException;
import com.bob.backend.exception.FileException;
import com.bob.backend.exception.UserException;
import com.bob.backend.mapper.UserMapper;
import com.bob.backend.model.MLoginRequest;
import com.bob.backend.model.MRegisterRequest;
import com.bob.backend.model.MRegisterResponse;
import com.bob.backend.service.TokenService;
import com.bob.backend.service.UserService;
import com.bob.backend.utill.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;
    private final TokenService tokenService;
    private final UserMapper userMapper;

    public UserBusiness(UserService userService, TokenService tokenService, UserMapper userMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    public String login(MLoginRequest request) throws BaseException {
        // validate request

        // verify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            // throw login fail, email not found
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            // throw login fail, password incorrect
            throw UserException.loginFailPasswordIncorrect();
        }

        return tokenService.tokenize(user);
    }

    public String refreshToken() throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }

         User user = optUser.get();
        return tokenService.tokenize(user);
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        return userMapper.toRegisterResponse(user);
    }

    public String uploadProfilePicture(MultipartFile file) throws BaseException {

        // validate file
        if (file == null) {
            //throw error
            throw FileException.fileNull();
        }

        // validate size
        if (file.getSize() > 1048576 * 2) {
            //throw error
            throw FileException.fileMaxSize();
        }


        // content type
        String contentType = file.getContentType();
        if (contentType == null) {
            // throw error
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg, image/png");
        if (!supportedTypes.contains(contentType)) {
            //throw error (unsupport)
            throw FileException.unsupported();
        }

        // TODO: upload file File Storage (AWS S3, etc...)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
