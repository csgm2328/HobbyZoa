package com.web.curation.follow.service;

import java.util.List;

import com.web.curation.follow.model.Follow;

public interface FollowService {
	Follow Follow(String from, String to);
	boolean Check(String from, String to);
	List<String> ShowFollowerList(String email);
	List<String> ShowFollowingList(String email);
}
