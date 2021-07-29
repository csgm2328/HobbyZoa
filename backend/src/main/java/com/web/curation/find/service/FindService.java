package com.web.curation.find.service;

import java.util.List;

import com.web.curation.user.model.User;

public interface FindService {

	List<User> findNickname(String nickname);
}
