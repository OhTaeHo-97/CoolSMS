package com.example.sms.controller;

import com.example.sms.dto.SmsDto.SmsRequestDto;
import com.example.sms.dto.SmsDto.VerificationDto;
import com.example.sms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sms")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity sendSms(@RequestBody SmsRequestDto smsRequest) {
        userService.sendSms(smsRequest.getTo());
        return new ResponseEntity("sms 전송 성공", HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity verifyVerificationCode(@RequestBody VerificationDto verificationInfo) {

        return new ResponseEntity(userService.verifyCode(verificationInfo), HttpStatus.OK);
    }
}
