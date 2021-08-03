package com.web.curation.like.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.like.model.FeedLike;

public interface LikeRepo extends JpaRepository<FeedLike, String>{
	Iterable<FeedLike> findAllByFeedcode(Integer feedcode);
	Integer countByFeedcode(Integer feedcode);
	Optional<FeedLike> findByEmailAndFeedcode(String email, Integer feedcode);
	int deleteByFeedcode(Integer feedcode);
}
