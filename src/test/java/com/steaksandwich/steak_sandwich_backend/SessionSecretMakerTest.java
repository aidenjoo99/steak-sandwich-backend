package com.steaksandwich.steak_sandwich_backend;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class SessionSecretMakerTest {

  @Test
  public void generateSecretKey() {
    SecretKey key = Jwts.SIG.HS512.key().build();
    String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
    System.out.printf("\nKey = [%s]\n", encodedKey);
  }
}