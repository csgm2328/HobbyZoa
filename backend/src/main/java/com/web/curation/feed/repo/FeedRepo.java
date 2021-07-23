package com.web.curation.feed.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.feed.model.Feed;
import com.web.curation.image.model.Image;

@Repository
public interface FeedRepo extends JpaRepository<Feed, Integer>{ //왜 Long일까, 우린 int니가 Integer로 하면 되나

	//findBy 뒤에 컬럼명을 붙이면 이를 이용한 검색 가능
	public List<Feed> findByFeedcode(int feedcode);
	//이미지 저장
	Image save(Image image);
	//모두 검색
	List<Image> findAllByFeedcode(int feedcode);
}

