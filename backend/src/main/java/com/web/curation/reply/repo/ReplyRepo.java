package com.web.curation.reply.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.reply.model.Reply;

@Repository
public interface ReplyRepo extends JpaRepository<Reply, Integer>{

	List<Reply> findAllByFeedcode(Integer feedcode);
	void deleteByReplycode(Integer replycode);
}
