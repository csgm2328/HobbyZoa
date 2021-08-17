package com.web.curation.reply.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.reply.model.Reply;
import com.web.curation.reply.repo.ReplyRepo;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyRepo replyRepo;
	
	@Override
	public Reply save(Reply reply) {
		return replyRepo.save(reply);
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
