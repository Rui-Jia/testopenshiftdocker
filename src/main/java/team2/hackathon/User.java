package team2.hackathon;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.annotation.processing.Generated;

public class User {

    //To help with auto increment. @GeneratedValue not available with MongoDB
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long userId;

    private String username;
    private String password;


    public User(long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("User [userId=%s, username=%s, password=%s]", userId, username, password);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if(o == null || o.getClass() != getClass()) {
            return false;
        }
        User u = (User) o;
        return this.userId == u.userId && this.username.equals(u.username) && this.password.equals(u.password);
    }
}
