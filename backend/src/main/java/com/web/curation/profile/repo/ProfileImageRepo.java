package com.web.curation.profile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.profile.model.ProfileImage;
public interface ProfileImageRepo extends JpaRepository<ProfileImage, String>{
}
