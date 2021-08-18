package com.web.curation.feed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.alarm.model.MessageType;
import com.web.curation.alarm.service.AlarmService;
import com.web.curation.feed.model.Feed;
import com.web.curation.feed.repo.FeedRepo;
import com.web.curation.image.model.Image;
import com.web.curation.image.repo.ImageRepo;
import com.web.curation.image.service.FileHandler;
import com.web.curation.like.model.FeedLike;
import com.web.curation.like.repo.LikeRepo;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;


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
	@Autowired
	AlarmService alarmService;
	
	@Override
	public List<Feed> findAllFeeds() { 
		List<Feed> feeds = new ArrayList<Feed>();
		feedRepo.findAll(Sort.by(Sort.Direction.DESC, "regtime")).forEach(e -> feeds.add(e));

		for (int i = 0; i < feeds.size(); i++) {
			List<Image> images = new ArrayList<Image>();
			imageRepo.findAllByFeed(feeds.get(i)).forEach(e -> images.add(e));
			feeds.get(i).setImages(images);
		}
		return feeds;
	}

	@Override
	public List<Feed> findByEmail(String email) { 
		List<Feed> feeds = feedRepo.findByEmailOrderByRegtimeDesc(email);
		for (int i = 0; i < feeds.size(); i++) {
			List<Image> images = new ArrayList<Image>();
			imageRepo.findAllByFeed(feeds.get(i)).forEach(e -> images.add(e));
			feeds.get(i).setImages(images);
		}
		return feeds;
	}
	
	@Override
	public Image findByNewname(String newname) {
		return imageRepo.findByNewname(newname);
	}
	
	@Override
	public List<Image> findAllByfeedcode(Integer feedcode){ 
		Feed feed = feedRepo.findByFeedcode(feedcode);
		return imageRepo.findAllByFeed(feed);
	}
	
	@Override 
	public Feed findByFeedcode(Integer feedcode) { 
		return feedRepo.findByFeedcode(feedcode); 
	}

	@Override
	public void deleteByFeedcode(Integer feedcode) { 
		feedRepo.deleteById(feedcode);
	}

	@Override
	public Feed save(Feed feed, List<MultipartFile> files ) throws Exception {
		Feed savedFeed = feedRepo.save(feed);
		
        List<Image> list = fileHandler.parseFileInfo(savedFeed, files);

        if(!list.isEmpty()){ 
            List<Image> imageList = new ArrayList<>();
            for(Image image : list) { 
            	Image savedImage = imageRepo.save(image); 
            	imageList.add(savedImage); 
            }
            feed.setImages(imageList);
        }
		return feed;
	}

	@Override
	public void updateByFeedcode(Integer feedcode, Feed feed, List<MultipartFile> files) throws Exception {
		Optional<Feed> e = feedRepo.findById(feedcode);
		Feed newfeed = feed;
		if(e.isPresent()) {
			e.get().setFeedcode(feed.getFeedcode());
			e.get().setEmail(feed.getEmail());
			e.get().setNickname(feed.getNickname());
			e.get().setComment(feed.getComment());
			e.get().setScrap(feed.getScrap());
			newfeed = feedRepo.save(feed);
		}

		imageRepo.deleteAllByFeed(newfeed);
		List<Image> list = fileHandler.parseFileInfo(newfeed, files);

        if(!list.isEmpty()){
            List<Image> imageList = new ArrayList<>();
            for(Image image : list) { 
            	Image savedImage = imageRepo.save(image);
            	imageList.add(savedImage);
            }
        }
	}

	@Override
	public void updateByFeedcodeNoImage(Integer feedcode, Feed feed) {
		Optional<Feed> e = feedRepo.findById(feedcode);
		if(e.isPresent()) {
			e.get().setFeedcode(feed.getFeedcode());
			e.get().setComment(feed.getComment());
			e.get().setScrap(feed.getScrap());
			e.get().setLikes(likeRepo.countByFeedcode(feedcode));
			feedRepo.save(e.get());
		}
	}
	
	@Override
	@Transactional
	public String LikeFeed(String email, Integer feedcode) {
		Optional<FeedLike> e = likeRepo.findByEmailAndFeedcode(email, feedcode);
		if(e.isPresent()) 
			if(likeRepo.deleteByEmailAndFeedcode(email, feedcode) != 0)
				return "좋아요 취소";
		likeRepo.save(
				FeedLike.builder().email(email).feedcode(feedcode).build());
		Feed feed = feedRepo.findByFeedcode(feedcode);
		updateByFeedcodeNoImage(feedcode, feed);
		
		Tag tag = new Tag();
		String alarmMsg = "";
		List<Feedtags> tags = feed.getFeedtags();
		if(tags.size() != 0) {
			tag = tags.get(0).getTag();
			alarmMsg = feed.getNickname() +"님이 " + tag.toString()+ "태그가 추가된 회원님의 피드를 좋아합니다.";
		}
		else
			alarmMsg = feed.getNickname() +"님이 회원님의 피드를 좋아합니다.";
		alarmService.createAlarm(MessageType.LIKE, email, feed.getEmail(), feedcode, alarmMsg);
		return "좋아요";
	}

	@Override
	public boolean CheckLike(String email, Integer feedcode) {
		return likeRepo.findByEmailAndFeedcode(email, feedcode).isPresent();
	}

	@Override
	public List<String> ShowLikeList(Integer feedcode) {
		List<String> likes = new ArrayList<String>();
		likeRepo.findAllByFeedcode(feedcode).forEach(e -> likes.add(e.getEmail()));
		return likes;
	}

	@Override
	public List<Feed> findByEmailInOrderByRegtimeDesc(List<String> list) {
		return feedRepo.findByEmailInOrderByRegtimeDesc(list);
	}

	@Override
	public List<Feed> getLikeFeedByEmail(String email) {
		List<Integer> list = new ArrayList<Integer>();
		likeRepo.findAllByEmail(email).forEach(e -> list.add(e.getFeedcode()));
		List<Feed> feeds = feedRepo.findByFeedcodeInOrderByRegtimeDesc(list);
		return feeds;
	}
}
