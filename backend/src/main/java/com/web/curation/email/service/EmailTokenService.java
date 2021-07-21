package com.web.curation.email.service;

import java.util.Optional;

import com.web.curation.email.model.EmailToken;

public interface EmailTokenService {
	EmailToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId);
	Optional<EmailToken> findById(String confirmationTokenId);
	
	String createEmailConfirmationToken(String userEmail, String receiverEmail);
	boolean confirmEmail(String token);
	void reCreateToken(String userEmail, String recieverEmail);
}
