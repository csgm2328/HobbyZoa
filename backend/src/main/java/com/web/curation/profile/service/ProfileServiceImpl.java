package com.web.curation.profile.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.profile.model.ProfileImage;
import com.web.curation.profile.repo.ProfileImageRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	ProfileImageRepo profileRepo;
	
	@Override
	public Optional<ProfileImage> findById(String email) {
		return profileRepo.findById(email);
	}

	@Override
	public ProfileImage save(ProfileImage image) {
		return profileRepo.save(image);
	}

}
