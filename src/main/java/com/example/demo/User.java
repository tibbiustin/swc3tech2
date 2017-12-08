package com.example.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coag on 24-10-2017.
 */
public class User {
    private final long id;
    private String name;
    private String email;

    private static List<User> userList = new ArrayList<>();

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static User getUserById(long id){
        List<User> list = User.getUserList();

        User theOne = new User(-1, "Dummy", "dummy@smart.com");

        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            if (u.getId() == id) {
                System.out.println(u);
                theOne = u;
                break;
            }
        }
        return theOne;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
