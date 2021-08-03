package com.web.curation.feed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.model.Feed;
import com.web.curation.feed.repo.FeedRepo;
import com.web.curation.image.model.Image;
import com.web.curation.image.repo.ImageRepo;
import com.web.curation.image.service.FileHandler;
import com.web.curation.like.model.FeedLike;
import com.web.curation.like.repo.LikeRepo;

@Service
public class FeedServiceImpl implements FeedService{

	@Autowired
	private FeedRepo feedRepo;
	
	@Autowired
	private ImageRepo imageRepo;
	
	@Autowired
	LikeRepo likeRepo;
	
	@Autowired
	FileHandler fileHandler;
	
	@Override
	public List<Feed> findAllFeeds() { //전체 레코드 불러오기 findAll()
		List<Feed> feeds = new ArrayList<Feed>();
		feedRepo.findAll(Sort.by(Sort.Direction.DESC, "regtime")).forEach(e -> feeds.add(e));

		for (int i = 0; i < feeds.size(); i++) {
			List<Image> images = new ArrayList<Image>();
			imageRepo.findAllByfeedcode(feeds.get(i).getFeedcode()).forEach(e -> images.add(e));
			feeds.get(i).setImages(images);
		}
		return feeds;
	}


	@Override
	public List<Feed> findByEmail(String email) { //해당 계정 피드 모아보기
		List<Feed> feeds = feedRepo.findByEmail(email);
		for (int i = 0; i < feeds.size(); i++) {
			List<Image> images = new ArrayList<Image>();
			imageRepo.findAllByfeedcode(feeds.get(i).getFeedcode()).forEach(e -> images.add(e));
			feeds.get(i).setImages(images);
		}
		return feeds;
	}
	
	@Override
	public Image findOneByfeedcode(Integer feedcode) { // 해당 피드코드 이미지 하나만 반환
		return imageRepo.findOneByfeedcode(feedcode);
	}
	
	@Override
	public Image findByNewname(String newname) { // 새이름으로 이미지 반환
		return imageRepo.findByNewname(newname);
	}
	
	@Override
	public List<Image> findAllByfeedcode(Integer feedcode){ //해당 피드코드 이미지 모두 반환
		return imageRepo.findAllByfeedcode(feedcode);
	}
	
	@Override 
	public Feed findByFeedcode(Integer feedcode) { //피드 누르면 상세보기 가능하도록
		Feed feed = feedRepo.findByFeedcode(feedcode);
		//좋아요수 업데이트
		feed.setLikes(likeRepo.countByFeedcode(feedcode));
		return feedRepo.findByFeedcode(feedcode); //수정 후 리턴
	}

	@Override
	public void deleteByFeedcode(Integer feedcode) { //레코드 삭제 delete()
		feedRepo.deleteById(feedcode);
	}

	@Override
	public Feed save(Feed feed, List<MultipartFile> files ) throws Exception {
		Feed savedFeed = feedRepo.save(feed);
		
        // 파일을 저장하고 그 image 에 대한 list 를 가지고 있는다
        List<Image> list = fileHandler.parseFileInfo(savedFeed.getFeedcode(), files);

        if(!list.isEmpty()){ // 파일 없는 경우는 없을 것, !list.isEmpty()로 바꿔서 코드 줄이기
            List<Image> imageList = new ArrayList<>();
            for(Image image : list) { //현재 list에는 새로운 이름으로 저장된 파일의 위치 리스트가 저장되어있다.
            	Image savedImage = imageRepo.save(image); //계속 null 던짐
            	imageList.add(savedImage); //imgcode까지 추가된 이미지 리스트
            }
        }
		return feed;
	}

	@Override
	public void updateByFeedcode(Integer feedcode, Feed feed, List<MultipartFile> files) throws Exception {
		Optional<Feed> e = feedRepo.findById(feedcode);
		
		if(e.isPresent()) {
			e.get().setFeedcode(feed.getFeedcode());
			e.get().setEmail(feed.getEmail());
			e.get().setNickname(feed.getNickname());
			e.get().setComment(feed.getComment());
			e.get().setLikes(likeRepo.countByFeedcode(feedcode));
			e.get().setScrap(feed.getScrap());
			feedRepo.save(feed);
		}
		//이미지 받아서 업데이트하는 과정 추가
		imageRepo.deleteAllByFeedcode(feedcode); //기존 이미지 삭제
		List<Image> list = fileHandler.parseFileInfo(feedcode, files);

        if(!list.isEmpty()){
            List<Image> imageList = new ArrayList<>();
            for(Image image : list) { 
            	Image savedImage = imageRepo.save(image); //새로운 이미지 저장
            	imageList.add(savedImage);
            }
        }
	}

	@Override
	public List<String> ShowLikesList(Integer feedcode) {
		List<String> likes = new ArrayList<String>();
		likeRepo.findAllByFeedcode(feedcode).forEach(e -> likes.add(e.getEmail()));
		return likes;
	}

	@Override
	public String LikeFeed(String email, Integer feedcode) {
		Optional<FeedLike> e = likeRepo.findByEmailAndFeedcode(email, feedcode);
		if(e.isPresent()) //이미 좋아요 했다면 취소
			if(likeRepo.deleteByFeedcode(feedcode) != 0)
				return "좋아요 취소";
		likeRepo.save(
				FeedLike.builder().email(email).feedcode(feedcode).build());
		return "좋아요";
	}

}
