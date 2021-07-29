package com.web.curation.find.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.find.model.SaveUser;
import com.web.curation.find.repo.FindHistoryRepo;
import com.web.curation.find.repo.FindRepo;
import com.web.curation.user.model.User;

@Service
public class FindServiceImpl implements FindService {
	

	@Autowired
	FindRepo findRepo;

	@Autowired
	FindHistoryRepo findHistoryRepo;

	@Override
	public List<User> findNickname(String nickname) {
		List<User> list = findRepo.findBynicknameContaining(nickname);
		return list;
	}

	@Override
	public List<SaveUser> showHistory(String email) {
		List<SaveUser> list = findHistoryRepo.findBySaveEmail(email);
		return list;
	}

	@Override
	public SaveUser saveHistory(SaveUser saveuser) {
		findHistoryRepo.save(saveuser);
		return null;
	}


}