package com.api.infrastructure.dto.jwt;

public class JwtDTO {

    private String jwt;

    public JwtDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
