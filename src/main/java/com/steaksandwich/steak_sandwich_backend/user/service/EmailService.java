package com.steaksandwich.steak_sandwich_backend.user.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

public interface EmailService {

    void sendEmail(String to, String subject, String text) throws MessagingException;
}
