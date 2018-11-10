package io.zuolizhu.sportswatchapp.Services;

import io.zuolizhu.sportswatchapp.Models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUserID(Long userID);
    void addAUser(User user);
}
