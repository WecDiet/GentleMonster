package com.gentlemonster.Service.Token;

public interface ITokenGeneratorService {
    String generateResetToken(String email);
    String generateRandomString();
}
