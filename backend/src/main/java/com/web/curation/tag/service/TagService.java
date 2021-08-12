package com.web.curation.tag.service;

import java.util.List;

import com.web.curation.feed.model.Feed;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;

public interface TagService {
	Tag findByTagname(String tagname);
	void updateTagCnt(Tag tag);
	void saveTag(Tag tag);
	void saveFeedtags(Feedtags feedtags);
	List<Feed> orderByLikes(String tagname);
	List<Feed> orderByRegtime(String tagname);
	List<Tag> findAllByFeedcode(Feed feed);
}
