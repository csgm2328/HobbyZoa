package com.web.curation.badge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.badge.model.Badge;
import com.web.curation.badge.repo.BadgeRepo;
import com.web.curation.hobby.model.Hobby;

@Service
public class BadgeServiceImpl implements BadgeService {

	@Autowired
	BadgeRepo badgeRepo;
	
	@Override
	public Badge save(Badge badge) {
		return badgeRepo.save(badge);
	}

	@Override
	public List<Badge> findAllByHobby(Hobby hobby) {
		return badgeRepo.findAllByHobby(hobby);
	}

	@Override
	public void deleteByHobbyAndName(Hobby hobby, String name) {
		badgeRepo.deleteByHobbyAndName(hobby, name);
	}

}
