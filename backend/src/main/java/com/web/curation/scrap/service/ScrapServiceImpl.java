package com.web.curation.scrap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.alarm.model.MessageType;
import com.web.curation.alarm.service.AlarmService;
import com.web.curation.feed.model.Feed;
import com.web.curation.feed.repo.FeedRepo;
import com.web.curation.scrap.model.Scrap;
import com.web.curation.scrap.repo.ScrapRepo;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;

@Service
public class ScrapServiceImpl implements ScrapService {

	@Autowired
	private ScrapRepo scrapRepo;

	@Autowired
	private FeedRepo feedRepo;

	@Autowired
	AlarmService alarmService;

	@Override
	public Scrap save(Scrap scrap) {
		Scrap result = scrapRepo.save(scrap);
		Feed feed = feedRepo.findByFeedcode(scrap.getFeedcode());

		Tag tag = new Tag();
		String alarmMsg = "";
		List<Feedtags> tags = feed.getFeedtags();
		if (tags.size() != 0) {
			tag = tags.get(0).getTag();
			alarmMsg = feed.getNickname() + "님이 " + tag.toString() + "태그가 추가된 회원님의 피드를 스크랩했습니다.";
		} else
			alarmMsg = feed.getNickname() + "님이 회원님의 피드를 스크랩했습니다.";
		alarmService.createAlarm(MessageType.SCRAP, scrap.getEmail(), feed.getEmail(), scrap.getFeedcode(), alarmMsg);
		return result;
	}

	@Override
	public List<Scrap> findByEmail(String email) {
		return scrapRepo.findByEmail(email);
	}

	@Override
	public Scrap findByScrapcode(Integer scrapcode) {
		return scrapRepo.findByScrapcode(scrapcode);
	}

	@Override
	public void deleteByScrapcode(Integer scrapcode) {
		scrapRepo.deleteById(scrapcode);
	}

	@Override
	public Boolean existsByEmailAndFeedcode(String email, Integer feedcode) {
		return scrapRepo.existsByEmailAndFeedcode(email, feedcode);
	}

	@Override
	public Scrap findByEmailAndFeedcode(String email, Integer feedcode) {
		return scrapRepo.findByEmailAndFeedcode(email, feedcode);
	}

	@Override
	public List<Feed> findByFeedcodeInOrderByRegtimeDesc(List<Integer> list) {
		return feedRepo.findByFeedcodeInOrderByRegtimeDesc(list);
	}
}
