package com.web.curation.tag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.feed.model.Feed;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;

@Repository
public interface FeedtagsRepo extends JpaRepository<Feedtags, Integer>{

	List<Feedtags> findByTag(Tag tag);
	List<Feedtags> findByFeed(Feed feed);
}
