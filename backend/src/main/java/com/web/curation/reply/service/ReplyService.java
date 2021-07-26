package com.web.curation.reply.service;

import java.util.List;

import com.web.curation.reply.model.Reply;

public interface ReplyService {
	
	//댓글작성
	Reply save(Reply reply);
	//댓글보기(feed에 해당하는 댓글들)
	List<Reply> findAllByFeedcode(Integer feedcode);
	//댓글수정
	void updateByReplycode(Integer replycode, Reply reply);
	//댓글삭제
	void deleteByReplycode(Integer replycode);
}
