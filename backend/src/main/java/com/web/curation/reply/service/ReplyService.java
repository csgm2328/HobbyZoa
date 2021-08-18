package com.web.curation.reply.service;

import java.util.List;

import com.web.curation.reply.model.Reply;

public interface ReplyService {
	
	Reply save(Reply reply);
	List<Reply> findAllByFeedcode(Integer feedcode);
	Reply findByReplycode(Integer replycode);
	void updateByReplycode(Integer replycode, Reply reply);
	void deleteByReplycode(Integer replycode);
}
