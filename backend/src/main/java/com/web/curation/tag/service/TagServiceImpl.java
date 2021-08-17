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
import com.web.curation.tag.repo.OrderFeedRepo;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepo tagRepo;

	@Autowired
	private FeedtagsRepo feedtagsRepo;

	@Autowired
	private OrderFeedRepo orderByFeedRepo;

	@Override
	public boolean existsByTagname(String tagname) {
		return tagRepo.existsByTagname(tagname);
	}
	
	@Override
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

	public List<Feed> orderByLikes(String tagname) {
		List<Tag> tag = tagRepo.findByTagnameContaining(tagname);
		Tag containTag = new Tag();
		List<Feed> feeds = new ArrayList<Feed>();
		List<Integer> feedcodes = new ArrayList<Integer>();
		for (int i = 0; i < tag.size(); i++) {
			containTag=tag.get(i);
			List<Feedtags> feedtags = feedtagsRepo.findByTag(containTag);
			for (int j = 0; j < feedtags.size(); j++) {
				int feedcode = feedtags.get(j).getFeed().getFeedcode();
				feedcodes.add(feedcode);
			}	
		}
		feeds = orderByFeedRepo.findByFeedcodeInOrderByLikesDesc(feedcodes);
		return feeds;
	}

	public List<Feed> orderByRegtime(String tagname) {
		List<Tag> tag = tagRepo.findByTagnameContaining(tagname);
		Tag dateTag = new Tag();
		List<Feed> feeds = new ArrayList<Feed>();
		List<Integer> feedcodes = new ArrayList<Integer>();
		
		for (int i = 0; i < tag.size(); i++) {
			dateTag = tag.get(i);
			List<Feedtags> feedtags = feedtagsRepo.findByTag(dateTag);
			for (int j = 0; j < feedtags.size(); j++) {
				int feedcode = feedtags.get(j).getFeed().getFeedcode();
				feedcodes.add(feedcode);
			}
		}
		feeds = orderByFeedRepo.findByFeedcodeInOrderByRegtimeDesc(feedcodes);
		return feeds;
	}

	@Override
	public List<Tag> orderByTagCnt() {
		return tagRepo.findTop10ByOrderByCntDesc();
	}

}