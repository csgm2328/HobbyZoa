package com.web.curation.feed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.model.Feed;
import com.web.curation.image.model.Image;

public interface FeedService {

	List<Feed> findAll();
	Image findOneByfeedcode(Integer feedcode);
	List<Image> findAllByfeedcode(Integer feedcode);
	Feed findByFeedcode(Integer feedcode);
	List<Feed> findByEmail(String email);
	void deleteByFeedcode(Integer feedcode);
	Feed save(Feed feed, List<MultipartFile> files ) throws Exception;
	void updateByFeedcode(Integer feedcode, Feed feed, List<MultipartFile> files) throws Exception;
	
	
}
