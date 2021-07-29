package com.web.curation.find.service;

import java.util.List;

import com.web.curation.find.model.SaveUser;
import com.web.curation.user.model.User;

public interface FindService {

	List<User> findNickname(String nickname);

	void saveHistory(SaveUser saveuser);
	
	List<SaveUser> showHistory(String email);

}