package com.mihaicraciun.kbn.kbn.services;

public interface UserService {

    User create(String fullName, String email, String password);

    User update(User user, String fullName, String email, String password);

    void save(User user);

    User findByEmail(String username);

    User guestUser();

    User currentUser();
}
