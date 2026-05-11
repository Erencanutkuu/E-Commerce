package com.atlascommerce.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "ad boş bırakılamaz")
    private String username;
    @NotBlank(message = "şifre boş bırakılamaz")
    private String password;
    @Email
    @NotBlank(message = "geçersiz e posta formatı")
    private String email;

}
