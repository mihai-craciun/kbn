package com.mihaicraicun.kbn.kbn.services;

import com.mihaicraicun.kbn.kbn.entities.User;

public interface UserService {

    User create(String fullName, String email, String password);

    User update(User user, String fullName, String email, String password);

    void save(User user);

    User findByEmail(String username);
}
