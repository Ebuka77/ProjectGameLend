package com.example.project;

public class HelperClass {
    String email, name, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public HelperClass() {

    }
}
