package com.web.curation.feed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.model.Feed;

public interface FeedService {

	List<Feed> findAll();
	Optional<Feed> findByFeedcode(Integer feedcode);
	void deleteByFeedcode(Integer feedcode);
	Feed save(Feed feed, List<MultipartFile> files ) throws Exception;
	void updateByFeedcode(Integer feedcode, Feed feed);
	
	
}
