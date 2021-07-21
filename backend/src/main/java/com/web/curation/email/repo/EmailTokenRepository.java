package com.web.curation.email.repo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.email.model.EmailToken;

public interface EmailTokenRepository extends JpaRepository<EmailToken,String> {
    Optional<EmailToken> findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId, LocalDateTime now, boolean expired);
//    @Modifying
//    @Query("delete from confirmation_token")
	long deleteByuserEmail(String userEmail);
}