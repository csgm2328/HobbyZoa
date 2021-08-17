package com.web.curation.image.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.feed.model.Feed;
import com.web.curation.image.model.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer>{

    List<Image> findAllByFeed(Feed feed);
    Image findOneByFeed(Feed feed);
    Image findByNewname(String newname);
    @Transactional
    void deleteAllByFeed(Feed feed);
}
