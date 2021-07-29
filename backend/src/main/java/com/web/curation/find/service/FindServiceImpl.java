package com.web.curation.find.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.feed.model.Feed;
import com.web.curation.find.model.SaveUser;
import com.web.curation.find.repo.FindRepo;
import com.web.curation.find.repo.FindHistoryRepo;
import com.web.curation.user.model.User;

@Service
public class FindServiceImpl implements FindService {

	@Autowired
	FindRepo findRepo;

	@Autowired
	FindHistoryRepo saveFindRepo;

	@Override
	public List<User> findNickname(String nickname) {
		List<User> list = findRepo.findBynicknameContaining(nickname);
		return list;
	}

	@Override
	public List<SaveUser> showHistory(String email) {
		List<SaveUser> list = saveFindRepo.findBySaveEmail(email);
		return list;
	}

	@Override
	public void saveHistory(SaveUser saveuser) {
		saveFindRepo.save(saveuser);

	}

}
