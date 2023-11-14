package com.onlinebookstore.onlinebookstore.entities.rest;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";


    public JwtAuthenticationResponse() {
        this.accessToken = null;
    }

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
