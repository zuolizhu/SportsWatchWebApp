package io.zuolizhu.sportswatchapp.Services;

import io.zuolizhu.sportswatchapp.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional findUserByUserEmail();
}
