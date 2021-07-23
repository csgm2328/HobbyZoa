package com.web.curation.profile.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.web.curation.profile.model.ProfileImage;

public interface ProfileService {
	Optional<ProfileImage> findById(String email);
	ProfileImage save(String email, MultipartFile file) throws IllegalStateException, IOException;
}
