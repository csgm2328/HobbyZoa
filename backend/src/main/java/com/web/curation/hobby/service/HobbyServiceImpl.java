package com.web.curation.hobby.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.hobby.model.Hobby;
import com.web.curation.hobby.repo.HobbyRepo;

@Service
public class HobbyServiceImpl implements HobbyService {

	@Autowired
	HobbyRepo hobbyRepo;
	
	@Override
	public Hobby save(Hobby hobby) {
		return hobbyRepo.save(hobby);
	}

}
