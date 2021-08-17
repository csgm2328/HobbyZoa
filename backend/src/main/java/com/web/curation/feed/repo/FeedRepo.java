package com.web.curation.feed.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.feed.model.Feed;

@Repository
public interface FeedRepo extends JpaRepository<Feed, Integer>{ 

	List<Feed> findByEmailOrderByRegtimeDesc(String email);
	Feed findByFeedcode(Integer feedcode);
	int countByEmail(String email);
	List<Feed> findByEmailInOrderByRegtimeDesc(List<String> list);
	List<Feed> findByFeedcodeInOrderByRegtimeDesc(List<Integer> list);
}

