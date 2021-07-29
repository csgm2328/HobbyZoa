package com.web.curation.find.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.find.repo.FindRepo;
import com.web.curation.user.model.User;

@Service
public class FindServiceImpl implements FindService {
	
	@Autowired
	FindRepo findRepo;
	
	public List<User> findNickname(String nickname){
		List<User> list = findRepo.findBynicknameContaining(nickname);
		return list;
	}

}
