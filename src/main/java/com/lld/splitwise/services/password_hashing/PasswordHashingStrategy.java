package com.lld.splitwise.services.password_hashing;

public interface PasswordHashingStrategy {
    String hash(String originalPassword);
}
