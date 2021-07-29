package com.web.curation.like.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.like.model.Like;

public interface LikeRepo extends JpaRepository<Like, String>{
	Iterable<Like> findAllByFeedcode(Integer feedcode);
	Integer countByFeedcode(Integer feedcode);
	Like findByEmailandFeedcode(String email, Integer feedcode);
	int deleteByFeedcode(Integer feedcode);
}
