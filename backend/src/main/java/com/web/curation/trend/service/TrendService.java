package com.web.curation.trend.service;

import java.util.List;

import com.web.curation.feed.model.Feed;
import com.web.curation.tag.model.Tag;

public interface TrendService {
	List<Feed> orderByLikes(String tagname);
	List<Feed> orderByRegtime(String tagname);
}
