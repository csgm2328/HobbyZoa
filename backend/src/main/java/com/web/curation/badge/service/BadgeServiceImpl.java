package com.web.curation.badge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.badge.model.Badge;
import com.web.curation.badge.repo.BadgeRepo;

@Service
public class BadgeServiceImpl implements BadgeService {

	@Autowired
	BadgeRepo badgeRepo;
	
	@Override
	public Badge save(Badge badge) {
		return badgeRepo.save(badge);
	}

}
