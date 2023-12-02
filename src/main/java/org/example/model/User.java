package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String surname;
    private List<User> user = new ArrayList<>();
    public List<User> getUser(){
        return user;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
