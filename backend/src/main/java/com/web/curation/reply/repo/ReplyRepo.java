package com.web.curation.reply.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.reply.model.Reply;

@Repository
public interface ReplyRepo extends JpaRepository<Reply, Integer>{

	//생성(저장), 읽기, 수정, 삭제
	List<Reply> findAllByFeedcode(Integer feedcode);
	Reply findByReplycode(Integer replycode);
//	void updateByReplycode(Integer replycode);
	void deleteByReplycode(Integer replycode);
}
