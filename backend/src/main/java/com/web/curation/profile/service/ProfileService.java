package com.web.curation.profile.service;

import java.util.Optional;

import com.web.curation.profile.model.ProfileImage;

public interface ProfileService {
	Optional<ProfileImage> findById(String email);
	ProfileImage save(ProfileImage image);
}
