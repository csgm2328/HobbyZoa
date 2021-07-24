package com.web.curation.profile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.profile.model.Profile;

public interface ProfileRepo extends JpaRepository<Profile, String>{

}
