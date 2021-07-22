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
import com.web.curation.image.service.FileHandler;

@Service
public class FeedServiceImpl implements FeedService{

	@Autowired
	private FeedRepo feedRepo;
	
	@Autowired
	FileHandler fileHandler;
	
	@Override
	public List<Feed> findAll() { //전체 레코드 불러오기 findAll()
		List<Feed> feeds = new ArrayList<Feed>();
		feedRepo.findAll().forEach(e -> feeds.add(e));
		return feeds;
	}

	@Override
	public Optional<Feed> findByFeedcode(Integer feedcode) { //일단 보류
		Optional<Feed> feed = feedRepo.findById(feedcode);
		return null;
	}

	@Override
	public void deleteByFeedcode(Integer feedcode) { //레코드 삭제 delete()
		feedRepo.deleteById(feedcode);
	}

	@Override
	public Feed save(Feed feed, List<MultipartFile> files ) throws Exception {
        // 파일을 저장하고 그 image 에 대한 list 를 가지고 있는다
        List<Image> list = fileHandler.parseFileInfo(feed.getFeedcode(), files); //save를 하려면 feedcode가 필요,,,

        if(list.isEmpty()){
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<Image> imageList = new ArrayList<>();
            for(Image image : list) { //현재 list에는 새로운 이름으로 저장된 파일의 위치 리스트가 저장되어있다.
            	imageList.add(feedRepo.save(image));
            }
            feed.setMetadata(imageList);
        }

//        feed.setReported_date(new Date().toString());
		feedRepo.save(feed);
		return feed;
	}

	@Override
	public void updateByFeedcode(Integer feedcode, Feed feed) {
		Optional<Feed> e = feedRepo.findById(feedcode);
		
		if(e.isPresent()) {
			e.get().setFeedcode(feed.getFeedcode());
			e.get().setEmail(feed.getEmail());
			e.get().setNickname(feed.getNickname());
			e.get().setRegtime(feed.getRegtime());
			e.get().setComment(feed.getComment());
			e.get().setMetadata(feed.getMetadata());
			e.get().setLikes(feed.getLikes());
			e.get().setScrap(feed.getScrap());
		}
		
	}

}
