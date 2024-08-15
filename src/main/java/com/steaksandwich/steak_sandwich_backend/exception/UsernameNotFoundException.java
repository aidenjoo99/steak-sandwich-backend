package com.steaksandwich.steak_sandwich_backend.exception;

public class UsernameNotFoundException extends RuntimeException {
  
  public UsernameNotFoundException(String message) {
    super(message);
  }
}
