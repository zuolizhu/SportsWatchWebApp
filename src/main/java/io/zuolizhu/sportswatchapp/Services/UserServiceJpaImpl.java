package io.zuolizhu.sportswatchapp.Services;

import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceJpaImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findByUserID(Long userID) {
        return this.userRepository.findAll().stream()
                .filter(p -> Objects.equals(p.getUserID(), userID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addAUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void updateUserFavoriteTeams(User user) {
        this.userRepository.save(user);
    }

}
