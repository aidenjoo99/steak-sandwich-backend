package com.steaksandwich.steak_sandwich_backend.session.entity;

public class LoginForm {
  private String username;
  private String password;

  public LoginForm(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}

