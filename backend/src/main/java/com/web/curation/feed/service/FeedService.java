package com.web.curation.feed.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.model.Feed;
import com.web.curation.image.model.Image;

public interface FeedService {

	List<Feed> findAllFeeds();
	List<Image> findAllImages();
	Image findOneByfeedcode(Integer feedcode);
	List<Image> findAllByfeedcode(Integer feedcode);
	Feed findByFeedcode(Integer feedcode);
	List<Feed> findByEmail(String email);
	void deleteByFeedcode(Integer feedcode);
	Feed save(Feed feed, List<MultipartFile> files ) throws Exception;
	void updateByFeedcode(Integer feedcode, Feed feed, List<MultipartFile> files) throws Exception;
	List<String> ShowLikesList(Integer feedcode);
	String LikeFeed(String email ,Integer feedcode);
}
