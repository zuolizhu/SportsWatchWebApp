package io.zuolizhu.sportswatchapp.Services;

import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUserID(Long userID);
    void updateFavoriteTeams(Long userID, List<Team> favoriteTeams);
    void addAUser(User user);
}
