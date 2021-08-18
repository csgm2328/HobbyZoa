package com.web.curation.reply.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.alarm.model.MessageType;
import com.web.curation.alarm.service.AlarmService;
import com.web.curation.feed.model.Feed;
import com.web.curation.feed.repo.FeedRepo;
import com.web.curation.reply.model.Reply;
import com.web.curation.reply.repo.ReplyRepo;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;
import com.web.curation.user.model.User;
import com.web.curation.user.repo.UserRepo;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyRepo replyRepo;
	@Autowired
	private FeedRepo feedRepo;
	@Autowired
	private AlarmService alarmService;
	@Autowired
	UserRepo userRepo;
	
	@Override
	public Reply save(Reply reply) {
		Reply result = replyRepo.save(reply);
		Feed feed = feedRepo.findByFeedcode(reply.getFeedcode());
		Optional<User> u = userRepo.findById(reply.getEmail());
		
		String alarmMsg = "";
		List<Feedtags> tags = feedtagsRepo.findByFeed(feed);
		if(tags.size() != 0) {
			Tag tag = tags.get(0).getTag();
			alarmMsg = u.get().getNickname() +"님이 " + tag.getTagname() + "태그가 추가된 회원님의 피드를 좋아합니다.";
		} else
			alarmMsg = u.get().getNickname() + "님이 회원님의 피드에 댓글을 달았습니다.";
		alarmService.createAlarm(MessageType.SCRAP, reply.getEmail(), feed.getEmail(), reply.getFeedcode(), alarmMsg);
		return result;
	}

	@Override
	public List<Reply> findAllByFeedcode(Integer feedcode) {
		return replyRepo.findAllByFeedcode(feedcode);
	}

	public Reply findByReplycode(Integer replycode) {
		Optional<Reply> e = replyRepo.findById(replycode);
		return e.get();
	}
	
	@Override
	public void updateByReplycode(Integer replycode, Reply reply) {
		Optional<Reply> e = replyRepo.findById(replycode);
		
		if(e.isPresent()) {
			e.get().setReplycode(reply.getReplycode());
			e.get().setEmail(reply.getEmail());
			e.get().setNickname(reply.getNickname());
			e.get().setContent(reply.getContent());
			e.get().setHide(reply.getHide());
			e.get().setFeedcode(reply.getFeedcode());
			replyRepo.save(e.get());
		}
		
	}

	@Override
	public void deleteByReplycode(Integer replycode) {
		replyRepo.deleteById(replycode);
	}

}
