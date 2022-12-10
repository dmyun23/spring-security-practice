package me.benny.practice.spring.security.user;

public class AlreadyRegisteredUserExcepiton extends RuntimeException{

    public AlreadyRegisteredUserExcepiton(String message) {
        super(message);
    }

    public AlreadyRegisteredUserExcepiton() {
        super("이미 등록된 유저입니다.");
    }
}
