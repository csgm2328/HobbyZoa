package com.web.curation.hobby.service;

import java.util.List;

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

	@Override
	public List<Hobby> findAllByEmail(String email) {
		return hobbyRepo.findAllByEmail(email);
	}

	@Override
	public void deleteByHobbycode(Integer hobbycode) {
		hobbyRepo.deleteById(hobbycode);
	}

}
