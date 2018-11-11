package io.zuolizhu.sportswatchapp.Models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String userName;
    private String password;
    private boolean admin;
    private ArrayList<Long> favoriteTeams;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.admin = false;
        ArrayList<Long> defaultTeams = new ArrayList<>();
        defaultTeams.add(0L);
        this.favoriteTeams = defaultTeams;
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

    public ArrayList<Long> getFavoriteTeams() {
        return favoriteTeams;
    }

    public void setFavoriteTeams(ArrayList<Long> favoriteTeams) {
        this.favoriteTeams = favoriteTeams;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
