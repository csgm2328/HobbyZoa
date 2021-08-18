package com.web.curation.scrap.service;

import java.util.List;

import com.web.curation.feed.model.Feed;
import com.web.curation.scrap.model.Scrap;

public interface ScrapService {

	Scrap save(Scrap scrap);
	List<Scrap> findByEmail(String email);
	Scrap findByScrapcode(Integer scrapcode);
	void deleteByScrapcode(Integer scrapcode);
	Boolean existsByEmailAndFeedcode(String email, Integer feedcode);
	Scrap findByEmailAndFeedcode(String email, Integer feedcode);
	List<Feed> findByFeedcodeInOrderByRegtimeDesc(List<Integer> list);
}
