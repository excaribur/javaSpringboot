package com.bob.backend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user." + code);
    }

    // user.register.request.null
    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    // user.register.email.null
    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    // CREATE
    public static UserException createEmailNull() {
        return new UserException("create.email.null");
    }

    public static UserException createEmailDuplicated() {
        return new UserException("create.email.Duplicated");
    }

    public static UserException createPasswordNull() {
        return new UserException("create.Password.null");
    }

    public static UserException createNameNull() {
        return new UserException("create.Name.null");
    }


    // LOGIN
    public static  UserException loginFailEmailNotFound() {
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrect() {
        return new UserException("login.fail");
    }


}
