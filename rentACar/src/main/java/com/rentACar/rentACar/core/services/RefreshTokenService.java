package com.rentACar.rentACar.core.services;

import com.rentACar.rentACar.entities.concretes.RefreshToken;
import com.rentACar.rentACar.entities.concretes.User;
import com.rentACar.rentACar.repositories.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;



@Service
public class RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    Long expireSecond;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createRefreshToken(User user){
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusSeconds(expireSecond));
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    public RefreshToken getByUser(int userId){
        return refreshTokenRepository.findByUserId(userId);
    }


}
