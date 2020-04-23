package com.example.demo.dto;

public class UserDTO {
    private String email;
    private String username;
    private String fullname;
    private String password;
    private int status;

    public UserDTO() {
    }

    public UserDTO(String email, String username, String fullname, String password, int status) {
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
