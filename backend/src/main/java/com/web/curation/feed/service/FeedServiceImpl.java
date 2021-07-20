package com.web.curation.feed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.feed.model.Feed;
import com.web.curation.feed.repo.FeedRepo;

@Service
public class FeedServiceImpl implements FeedService{

	@Autowired
	private FeedRepo feed_repo;
	
	@Override
	public List<Feed> findAll() { //전체 레코드 불러오기 findAll()
		List<Feed> feeds = new ArrayList<Feed>();
		feed_repo.findAll().forEach(e -> feeds.add(e));
		return feeds;
	}

	@Override
	public Optional<Feed> findByFeedcode(Integer feedcode) { //일단 보류
		Optional<Feed> feed = feed_repo.findById(feedcode);
		return null;
	}

	@Override
	public void deleteByFeedcode(Integer feedcode) { //레코드 삭제 delete()
		feed_repo.deleteById(feedcode);
	}

	@Override
	public Feed save(Feed feed) { //레코드 저장(insert, update) save()
		feed_repo.save(feed);
		return feed;
	}

	@Override
	public void updateByFeedcode(Integer feedcode, Feed feed) {
		Optional<Feed> e = feed_repo.findById(feedcode);
		
		if(e.isPresent()) {
			e.get().setFeedcode(feed.getFeedcode());
			e.get().setEmail(feed.getEmail());
			e.get().setNickname(feed.getNickname());
			e.get().setRegtime(feed.getRegtime());
			e.get().setComment(feed.getComment());
			e.get().setMetadata(feed.getMetadata());
			e.get().setLikes(feed.getLikes());
			e.get().setScrap(feed.getScrap());
		}
		
	}

}
