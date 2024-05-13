package com.example.sms.service;

import com.example.sms.dto.SmsDto.VerificationDto;
import com.example.sms.dto.SmsDto.VerificationResponseDto;
import com.example.sms.redis.entity.VerificationCode;
import com.example.sms.redis.repository.VerificationCodeRepository;
import com.example.sms.utils.SmsUtil;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private static final int VERIFICATION_CODE_LENGTH = 6;

    private final VerificationCodeRepository verificationCodeRepository;
    private final SmsUtil smsUtil;

    public void sendSms(String to) {
        String verificationCode = makeVerificationCode();
        smsUtil.sendOne(to, verificationCode);
        saveVerificationCodeWithUser(to, verificationCode);
    }

    private void saveVerificationCodeWithUser(String phone, String verificationCode) {
        verificationCodeRepository.save(VerificationCode.of(phone, verificationCode, 180000L));
    }

    private String makeVerificationCode() {
        String verificationCode = "";
        for (int count = 0; count < VERIFICATION_CODE_LENGTH; count++) {
            verificationCode += (int) (Math.random() * 10);
        }

        return verificationCode;
    }

    public VerificationResponseDto verifyCode(VerificationDto verificationInfo) {
        VerificationCode code = verificationCodeRepository.findById(verificationInfo.getPhone())
                .orElseThrow(() -> new IllegalArgumentException("인증번호가 만료되었습니다."));
        return new VerificationResponseDto(code.getCode().equals(verificationInfo.getVerificationCode()));
    }
}
