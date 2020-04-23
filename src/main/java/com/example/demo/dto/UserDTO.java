package com.example.demo.dto;

public class UserDTO {
    private String email;
    private String username;
    private String fullname;
    private int status;

    public UserDTO() {
    }

    public UserDTO(String email, String username, String fullname, int status) {
        this.email = email;
        this.username = username;
        this.fullname = fullname;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
