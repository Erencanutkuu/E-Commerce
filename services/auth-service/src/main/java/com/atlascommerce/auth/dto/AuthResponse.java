package com.atlascommerce.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor  // oto const yazar
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
