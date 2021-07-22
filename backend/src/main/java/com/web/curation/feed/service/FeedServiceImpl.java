package com.web.curation.feed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.model.Feed;
import com.web.curation.feed.repo.FeedRepo;
import com.web.curation.image.model.Image;
import com.web.curation.image.repo.ImageRepo;
import com.web.curation.image.service.FileHandler;

@Service
public class FeedServiceImpl implements FeedService{

	@Autowired
	private FeedRepo feedRepo;
	
	@Autowired
	private ImageRepo imageRepo;
	
	@Autowired
	FileHandler fileHandler;
	
	@Override
	public List<Feed> findAll() { //전체 레코드 불러오기 findAll(), 이미지를 어떻게 할까
		List<Feed> feeds = new ArrayList<Feed>();
		feedRepo.findAll().forEach(e -> feeds.add(e));
		return feeds;
	}

//	@Override
//	public List<Feed> findByEmail(String email) { //해당 이메일 피드 모아보기
//		List<Feed> feeds = new ArrayList<Feed>();
//		feedRepo.findAll().forEach(e -> feeds.add(e));
//		return feeds;
//	}
	
	@Override
	public List<Image> findByEmail(String email) { //해당 이메일 피드 모아보기, 이미지 테이블에 email 추가하면 간단하긴 한데..
		List<Feed> feeds = new ArrayList<Feed>();
		feedRepo.findAll().forEach(e -> feeds.add(e));
		List<Image> images = new ArrayList<Image>();
		for (int i = 0; i < feeds.size(); i++) {
			images.add(imageRepo.findOneByfeedcode(feeds.get(i).getFeedcode()));
		}
		return images;
	}
	
	@Override //피드 누르면 상세보기 가능하도록
	public Optional<Feed> findByFeedcode(Integer feedcode) { //일단 보류
		Optional<Feed> feed = feedRepo.findById(feedcode);
		return feed;
	}

	@Override
	public void deleteByFeedcode(Integer feedcode) { //레코드 삭제 delete()
		feedRepo.deleteById(feedcode);
	}

	@Override
	public Feed save(Feed feed, List<MultipartFile> files ) throws Exception {
		Feed savedFeed = feedRepo.save(feed);
		
        // 파일을 저장하고 그 image 에 대한 list 를 가지고 있는다
        List<Image> list = fileHandler.parseFileInfo(savedFeed.getFeedcode(), files); //save를 하려면 feedcode가 필요,,,

        if(list.isEmpty()){
            // 파일 없는 경우는 없을 것, !list.isEmpty()로 바꿔서 코드 줄이기
        }
        else{
            List<Image> imageList = new ArrayList<>();
            for(Image image : list) { //현재 list에는 새로운 이름으로 저장된 파일의 위치 리스트가 저장되어있다.
            	Image savedImage = imageRepo.save(image); //계속 null 던짐
            	imageList.add(savedImage);
            }
//            feed.setMetadata(imageList); //이걸 어떻게 처리할까
        }

//        feed.setReported_date(new Date().toString());
		
		return feed;
	}

	@Override
	public void updateByFeedcode(Integer feedcode, Feed feed) {
		Optional<Feed> e = feedRepo.findById(feedcode);
		
		if(e.isPresent()) {
			e.get().setFeedcode(feed.getFeedcode());
			e.get().setEmail(feed.getEmail());
			e.get().setNickname(feed.getNickname());
//			e.get().setRegtime(feed.getRegtime());
			e.get().setComment(feed.getComment());
//			e.get().setMetadata(feed.getMetadata());
			e.get().setLikes(feed.getLikes());
			e.get().setScrap(feed.getScrap());
			feedRepo.save(feed);
		}
		//이미지 받아서 업데이트하는 과정 추가
	}

}
