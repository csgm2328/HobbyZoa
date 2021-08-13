package com.web.curation.find.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.curation.find.model.History;
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
	public List<User> findSearchWord(String searchWord) {
		List<User> list = findRepo.findByNicknameContaining(searchWord);
		return list;
	}

	@Override
	public List<History> showHistory(String email) {
		List<History> list = findHistoryRepo.findByEmail(email);
		return list;
	}

	@Override
	public History saveHistory(History history) {
		return findHistoryRepo.save(history);
	}

}