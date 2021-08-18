package com.web.curation.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.feed.service.FeedService;
import com.web.curation.reply.model.Reply;
import com.web.curation.reply.service.ReplyService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/reply")
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	@Autowired
	FeedService feedService;

	@PostMapping
	@ApiOperation(value="댓글 생성", notes="댓글(reply)를 입력받아 uri를 반환")
	public ResponseEntity<?> save( @Valid @RequestParam("email") String email,
            @Valid @RequestParam("nickname") String nickname, @RequestParam("feedcode") Integer feedcode, 
            @RequestParam("content") String content, @RequestParam("hide") Boolean hide) throws Exception{
		Reply reply = replyService.save(Reply.builder()
				.email(email)
				.nickname(nickname)
				.content(content)
				.hide(hide)
				.feedcode(feedcode)
				.build());
		URI uriLocation = new URI("/reply/" + reply.getReplycode());
        return ResponseEntity.created(uriLocation).body("{}");
	}
	
	@PutMapping(value = "/{replycode}") 
	@ApiOperation(value="댓글 수정", notes="replycode로 수정 후 수정 피드 반환")
	public ResponseEntity<Reply> updateReply(@PathVariable("replycode") Integer replycode, 
			@RequestParam("content") String content, @RequestParam("hide") Boolean hide){ 
		Reply reply = replyService.findByReplycode(replycode);
		reply.setContent(content);
		reply.setHide(hide);
		replyService.updateByReplycode(replycode, reply); 		
		return new ResponseEntity<Reply>(reply, HttpStatus.OK); 
	}
	
	@DeleteMapping(value = "/{replycode}")
	@ApiOperation(value="댓글 삭제", notes="replycode로 삭제")
	public ResponseEntity<Void> deleteReply(@PathVariable("replycode") Integer replycode){
		replyService.deleteByReplycode(replycode);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}
}
