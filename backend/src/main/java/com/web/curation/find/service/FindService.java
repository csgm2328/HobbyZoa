package com.web.curation.find.service;

import java.util.List;

import com.web.curation.find.model.History;
import com.web.curation.user.model.User;

public interface FindService {

	List<User> findSearchWord(String nickname);

	History saveHistory(History saveuser);
	
	List<History> showHistory(String email);

}