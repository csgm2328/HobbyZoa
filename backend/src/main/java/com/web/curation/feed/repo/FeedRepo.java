package com.web.curation.feed.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.feed.model.Feed;

@Repository
public interface FeedRepo extends JpaRepository<Feed, Integer>{ //왜 Long일까, 우린 int니가 Integer로 하면 되나

	//모두 검색
	List<Feed> findByEmail(String email);
	//피드번호로 검색
	Feed findByFeedcode(Integer feedcode);
	//피드 저장
//	Feed save(Feed feed);
	// 프로필에서 피드수 리턴
	int countByEmail(String email);
	
}
