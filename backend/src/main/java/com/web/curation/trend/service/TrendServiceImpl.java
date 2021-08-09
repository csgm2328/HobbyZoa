package com.web.curation.trend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.feed.model.Feed;
import com.web.curation.tag.repo.TagRepo;
import com.web.curation.trend.repo.TrendRepo;

@Service
public class TrendServiceImpl implements TrendService {

	@Autowired
	private TrendRepo trendrepo;

	// 좋아요 순으로 내림차순 정렬
	public List<Feed> orderByLikes(String tagname) {
		return trendrepo.findByTagContainingOrderByLikesDesc(tagname);
	}
	
	// 날짜 순으로 내림차순 정렬
	public List<Feed> orderByRegtime(String tagname) {
		return trendrepo.findByTagContaining(tagname);
	}


}
