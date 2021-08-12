package com.web.curation.tag.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.curation.feed.model.Feed;


@Repository
public interface OrderByFeedRepo extends JpaRepository<Feed, Integer> {
	List<Feed> findAllByOrderByLikesDesc();
	List<Feed> findByFeedcodeInOrderByLikesDesc(List<Integer> list);
	List<Feed> findByFeedcodeInOrderByRegtimeDesc(List<Integer> list);
}