package com.example.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class SmsDto {
    @Getter
    public static class SmsRequestDto {
        private String to;
    }

    @Getter
    public static class VerificationDto {
        private String phone;
        private String verificationCode;
    }

    @Getter
    @AllArgsConstructor
    public static class VerificationResponseDto {
        private boolean isVerified;
    }
}
