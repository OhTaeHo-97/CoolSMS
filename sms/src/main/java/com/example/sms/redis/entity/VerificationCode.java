package com.example.sms.redis.entity;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("verificationCode")
@Builder
public class VerificationCode {
    @Id
    private String id;
    private String code;
    @TimeToLive
    private Long expiration;

    public static VerificationCode of(String id, String code, Long expiration) {
        return VerificationCode.builder()
                .id(id)
                .code(code)
                .expiration(expiration / 1000)
                .build();
    }
}
