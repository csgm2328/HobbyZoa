package com.web.curation.tag.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.curation.feed.model.Feed;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;
import com.web.curation.tag.service.TagService;


@Repository
public interface OrderByFeedRepo extends JpaRepository<Feed, Integer> {
	//List<Feed> findByTagnameContainingOrderByLikesDesc(String tagname);
	//Feed findByfeedcodeContaining(List<Feedtags> tags);
	List<Feed> findAllByOrderByLikesDesc();
	List<Feed> findByFeedcodeInOrderByLikesDesc(List<Integer> list);
}
