package com.learning.mongo.repository;

import com.learning.mongo.entity.OtpDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<OtpDetail,Long> {
    boolean existsByOtpAndTransactionId(String otp, String transaction_id);
}
