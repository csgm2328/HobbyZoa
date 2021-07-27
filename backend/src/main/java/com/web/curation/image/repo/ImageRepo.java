package com.web.curation.image.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.image.model.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer>{

	//해당 피드코드 이미지 모두 가져오기
    List<Image> findAllByfeedcode(Integer feedcode);
    //해당 피드코드 이미지 하나만 가져오기
    Image findOneByfeedcode(Integer feedcode);
    //새이름으로 이미지 가져오기
    Image findByNewname(String newname);
    @Transactional
    void deleteAllByFeedcode(Integer feedcode);
}
