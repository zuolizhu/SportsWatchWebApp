package io.zuolizhu.sportswatchapp.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String userName;
    private String password;
    private boolean admin;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Team> favoriteTeams = new ArrayList<>();

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.admin = false;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Team> getFavoriteTeams() {
        return favoriteTeams;
    }

    public void setFavoriteTeams(List<Team> favoriteTeams) {
        this.favoriteTeams = favoriteTeams;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", favoriteTeams=" + favoriteTeams +
                '}';
    }
}
