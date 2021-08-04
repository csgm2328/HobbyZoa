package com.web.curation.hobby.service;

import java.util.List;

import com.web.curation.hobby.model.Hobby;

public interface HobbyService {
	Hobby save(Hobby hobby);
	List<Hobby> findAllByEmail(String email);
}
