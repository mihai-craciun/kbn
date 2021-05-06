package com.mihaicraciun.kbn.kbn.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}