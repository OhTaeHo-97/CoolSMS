package com.example.sms.redis.repository;

import com.example.sms.redis.entity.VerificationCode;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {
}
