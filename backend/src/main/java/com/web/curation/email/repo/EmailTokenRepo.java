package com.web.curation.email.repo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.email.model.EmailToken;
public interface EmailTokenRepo extends JpaRepository<EmailToken,String> {
    Optional<EmailToken> findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId, LocalDateTime now, boolean expired);
//    @Modifying
//    @Query("delete from confirmation_token")
	long deleteByuserEmail(String userEmail);
}