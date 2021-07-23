package com.web.curation.profile.service;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.profile.model.ProfileImage;
import com.web.curation.profile.repo.ProfileImageRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	ProfileImageRepo profileRepo;
	@Autowired
	ProfileHandler profileHandler;
	
	@Override
	public Optional<ProfileImage> findById(String email) {
		return profileRepo.findById(email);
	}

	@Override
	public ProfileImage save(String email, MultipartFile file) throws IllegalStateException, IOException {
		return profileRepo.save(profileHandler.parseFileInfo(email, file));
	}

}
