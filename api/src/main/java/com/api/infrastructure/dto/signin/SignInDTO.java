package com.api.infrastructure.dto.signin;

public class SignInDTO {

    private String username;
    private String plainPassword;

   public SignInDTO(String username, String plainPassword) {
        this.username = username;
        this.plainPassword = plainPassword;
    }

    public SignInDTO() {

    }

    public String getUsername() {
        return username;
    }

    public String getPlainPassword() {
        return plainPassword;
    }
}
