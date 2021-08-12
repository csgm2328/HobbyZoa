package com.web.curation.badge.service;

import java.util.List;

import com.web.curation.badge.model.Badge;
import com.web.curation.hobby.model.Hobby;

public interface BadgeService {
	Badge save(Badge badge);
	List<Badge> findAllByHobby(Hobby hobby);
	void deleteByHobbyAndName(Hobby hobby, String name);
}
