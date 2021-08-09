package com.web.curation.tag.service;

import java.util.List;

import com.web.curation.feed.model.Feed;
import com.web.curation.tag.model.Tag;

public interface TagService {
	void updateTagCnt(String tagname);
	Tag check(String tagname);
	void saveTag(Tag tag);
	List<Feed> searchTag(String tagname);
}
