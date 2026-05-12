package com.atlascommerce.auth.controller;


import com.atlascommerce.auth.dto.AuthResponse;
import com.atlascommerce.auth.dto.LoginRequest;
import com.atlascommerce.auth.dto.RegisterRequest;
import com.atlascommerce.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //http istekleri karşılanır ve hepsini jsona donduru
@RequestMapping("/auth")   // tüm endpoitler auth ile başlar
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register") // sadece post istegini kabul eder
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {   //registerrequest e dondurur ordaki kuralları alır
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }


}
