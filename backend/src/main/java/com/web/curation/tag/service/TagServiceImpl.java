package com.web.curation.tag.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.curation.feed.model.Feed;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;
import com.web.curation.tag.repo.TagRepo;
import com.web.curation.tag.repo.FeedtagsRepo;
import com.web.curation.tag.repo.OrderByFeedRepo;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepo tagRepo;

	@Autowired
	private FeedtagsRepo feedtagsRepo;

	@Autowired
	private OrderByFeedRepo orderByFeedRepo;

	public Tag findByTagname(String tagname) {
		return tagRepo.findByTagname(tagname);
	}

	@Override
	public void saveTag(Tag tags) {
		tagRepo.save(tags);
		return;
	}

	@Override
	public void saveFeedtags(Feedtags feedtags) {
		feedtagsRepo.save(feedtags);
		return;
	}
	
	@Override
	public List<Tag> findAllByFeedcode(Feed feed) {
		List<Feedtags> feedtags = feedtagsRepo.findByFeed(feed);
		List<Tag> tags = new ArrayList<Tag>();

		for (int i = 0; i < feedtags.size(); i++) {
			tags.add(feedtags.get(i).getTag());
		}
		return tags;
	}	

	@Override
	public void updateTagCnt(Tag tag) {
		tagRepo.updateTagCnt(tag.getTagname());
	}

	// 좋아요 순으로 내림차순 정렬
	public List<Feed> orderByLikes(String tagname) {
		// tag테이블에서 tagname찾고 tag 가져옴
		Tag tag = tagRepo.findByTagnameContaining(tagname);
		List<Feed> feeds = new ArrayList<Feed>();
		// feedtags테이블에서 tag객체로 tagcode를 찾음
		List<Feedtags> feedtags = feedtagsRepo.findByTag(tag);
		List<Integer> feedcodes = new ArrayList<Integer>();
		for (int i = 0; i < feedtags.size(); i++) {
			int feedcode = feedtags.get(i).getFeed().getFeedcode();
			feedcodes.add(feedcode);
		}
		feeds = orderByFeedRepo.findByFeedcodeInOrderByLikesDesc(feedcodes);
		return feeds;
	}

	// 날짜 순으로 내림차순 정렬
	public List<Feed> orderByRegtime(String tagname) {
		Tag tag = tagRepo.findByTagnameContaining(tagname);
		List<Feed> feeds = new ArrayList<Feed>();
		List<Feedtags> feedtags = feedtagsRepo.findByTag(tag);
		List<Integer> feedcodes = new ArrayList<Integer>();
		for (int i = 0; i < feedtags.size(); i++) {
			int feedcode = feedtags.get(i).getFeed().getFeedcode();
			feedcodes.add(feedcode);
		}
		feeds = orderByFeedRepo.findByFeedcodeInOrderByRegtimeDesc(feedcodes);
		return feeds;
	}

	@Override
	public List<Tag> orderByTagCnt() {
		return tagRepo.findTop10ByOrderByCntDesc();
	}

}