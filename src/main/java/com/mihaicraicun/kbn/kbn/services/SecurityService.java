package com.mihaicraicun.kbn.kbn.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}