package com.web.curation.feed.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.model.Feed;
import com.web.curation.image.model.Image;

public interface FeedService {

	List<Feed> findAllFeeds();
	Image findOneByfeedcode(Integer feedcode);
	Image findByNewname(String newname);
	List<Image> findAllByfeedcode(Integer feedcode);
	Feed findByFeedcode(Integer feedcode);
	List<Feed> findByEmail(String email);
	void deleteByFeedcode(Integer feedcode);
	Feed save(Feed feed, List<MultipartFile> files ) throws Exception;
	void updateByFeedcode(Integer feedcode, Feed feed, List<MultipartFile> files) throws Exception;
	String LikeFeed(String email ,Integer feedcode);
	boolean CheckLike(String email, Integer feedcode);
	List<String> ShowLikeList(Integer feedcode);
	List<Feed> findByEmailInOrderByRegtimeDesc(List<String> list);
	List<Feed> getLikeFeedByEmail(String email);
}
