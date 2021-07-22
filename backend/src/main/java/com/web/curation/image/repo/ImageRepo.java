package com.web.curation.image.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.image.model.Image;

public interface ImageRepo extends JpaRepository<Image, Integer>{

	Image save(Image image);

    List<Image> findAllByfeedcode(Integer feedcode);
}
