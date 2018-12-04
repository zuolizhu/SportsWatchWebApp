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
    private String userEmail;
    private boolean admin;

    public User() {
    }

    public ArrayList<String> getFavoriteTeams() {
        return favoriteTeams;
    }

    public void setFavoriteTeams(ArrayList<String> favoriteTeams) {
        this.favoriteTeams = favoriteTeams;
    }

    private ArrayList<String> favoriteTeams;

    public User(Long userID, String userName, String userEmail) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", admin=" + admin +
                ", favoriteTeams=" + favoriteTeams +
                '}';
    }
}
