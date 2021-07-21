package com.web.curation.email.service;

import java.util.Optional;

import com.web.curation.email.model.EmailToken;

public interface EmailTokenService {
	public String createEmailConfirmationToken(String userEmail, String receiverEmail);
	public EmailToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId);
	public Optional<EmailToken> findById(String confirmationTokenId);
}
