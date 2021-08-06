package com.web.curation.email.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {
	void sendEmail(SimpleMailMessage msg);
}
