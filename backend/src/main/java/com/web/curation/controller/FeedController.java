package com.web.curation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.model.Feed;
import com.web.curation.feed.service.FeedService;
import com.web.curation.image.model.Image;
import com.web.curation.reply.model.Reply;
import com.web.curation.reply.service.ReplyService;
import com.web.curation.response.BasicResponse;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;
import com.web.curation.tag.service.TagService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/feed")
public class FeedController {

	@Autowired
	FeedService feedService;

	@Autowired
	ReplyService replyService;

	@Autowired
	TagService tagService;

	@PostMapping
	@ApiOperation(value = "피드 생성", notes = "email, nickname, comment와 file리스트를 입력받아 uri를 반환")
	public ResponseEntity<?> createFeed(@Valid @RequestParam("email") String email,
			@Valid @RequestParam("nickname") String nickname, @RequestParam("comment") String comment,
          @Valid @RequestPart("files") List<MultipartFile> files,
          @RequestParam(value="tags", required=false) List<String> tags) throws Exception {        
        Feed feed = feedService.save(Feed.builder().email(email).nickname(nickname).comment(comment).build(), files);
        
		for (int i = 0; i < tags.size(); i++) {
			String tagname = tags.get(i);
			boolean tagCheck = tagService.existsByTagname(tagname);
			Tag tag = new Tag();
			if (tagCheck) {
				Tag existTag = tagService.findByTagname(tagname);
				existTag.setTagcode(existTag.getTagcode());
				existTag.setTagname(existTag.getTagname());
				existTag.setCnt(existTag.getCnt()+1);
				tagService.saveFeedtags(Feedtags.builder().feed(feed).tag(existTag).build());
			} else {
				tag.setTagname(tagname);
				tag.setCnt(1);
				tagService.saveTag(tag);
				tagService.saveFeedtags(Feedtags.builder().feed(feed).tag(tag).build());
			}
		}

		URI uriLocation = new URI("/feed/" + feed.getFeedcode());
		return ResponseEntity.created(uriLocation).body("{}");

	}

	@GetMapping(value = "/all")
	@ApiOperation(value = "모든 피드 조회", notes = "모든 피드 반환")
	public ResponseEntity<List<Feed>> getAllFeeds() {
		List<Feed> feeds = feedService.findAllFeeds();
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
	}

	@GetMapping(value = "/mine")
	@ApiOperation(value = "해당 계정의 모든 피드 조회", notes = "계정 이메일 받아서 해당 계정의 피드와 이미지 엮어서 반환")
	public ResponseEntity<List<Feed>> getFeedsByEmail(@RequestParam("email") String email) {
		List<Feed> list = feedService.findByEmail(email);
		return new ResponseEntity<List<Feed>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/search/{feedcode}")
	@ApiOperation(value = "해당 feedcode의 피드 상세보기", notes = "피드 정보 담긴 객체와 이미지리스트 반환")
	public ResponseEntity<Map<String, Object>> getFeedByFeedcode(@PathVariable("feedcode") Integer feedcode) {
		Map<String, Object> map = new HashMap<>();
		Feed feed = feedService.findByFeedcode(feedcode);
		List<Image> images = feedService.findAllByfeedcode(feedcode);
		List<Reply> replies = replyService.findAllByFeedcode(feedcode);
		List<Tag> tags = tagService.findAllByFeedcode(feed);
		List<String> taginfo = new ArrayList<String>();
		for (int i = 0; i < tags.size(); i++) {
			taginfo.add(tags.get(i).getTagname());
		}
		map.put("feed", feed);
		map.put("images", images);
		map.put("replies", replies);
		map.put("tags", taginfo);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{feedcode}")
	@ApiOperation(value = "피드 삭제", notes = "feedcode로 삭제")
	public ResponseEntity<Void> deleteFeed(@PathVariable("feedcode") Integer feedcode) {
		feedService.deleteByFeedcode(feedcode);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/{feedcode}")
	@ApiOperation(value = "피드 수정", notes = "feedcode로 수정 후 수정 피드 반환")
	public ResponseEntity<Feed> updateFeed(@PathVariable("feedcode") Integer feedcode,
			@RequestParam("comment") String comment, @RequestPart(value="files", required=false) List<MultipartFile> files) throws Exception {
		Feed feed = feedService.findByFeedcode(feedcode);
		feed.setComment(comment);
		if(files==null) {
			feedService.updateByFeedcodeNoImage(feedcode, feed);
		}else {
			feedService.updateByFeedcode(feedcode, feed, files);
		}
		return new ResponseEntity<Feed>(feed, HttpStatus.OK);
	}

	@GetMapping(value = "{newname}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> imageSearch(@PathVariable("newname") String newname) throws IOException {
		Image image = feedService.findByNewname(newname);
		String absolutePath = new File("").getAbsolutePath() + "/"; // 리눅스 버전
		InputStream imageStream = new FileInputStream(absolutePath + image.getImgpath());
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}

	@GetMapping("/like/{email}/{feedcode}")
	@ApiOperation(value = "좋아요 기능", notes = "이미 좋아요 했다면 취소됨")
	public ResponseEntity<String> LikeFeed(@PathVariable("email") String email,
			@PathVariable("feedcode") Integer feedcode) {
		String result = email + "님이 피드번호: " + feedcode + "을 " + feedService.LikeFeed(email, feedcode);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("checklike/{email}/{feedcode}")
	@ApiOperation(value = "좋아요 여부 체크", notes = "")
	public ResponseEntity<BasicResponse> checkLike(@PathVariable("email") String email,
			@PathVariable("feedcode") Integer feedcode) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();

		if (feedService.CheckLike(email, feedcode)) {
			result.status = true;
			result.data = "[" + email + "] 가 [" + feedcode + "]를 좋아요 중입니다.";
		} else {
			result.status = false;
			result.data = "[" + email + "] 가 [" + feedcode + "]를 좋아요 중이 아닙니다.";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/likelist/{feedcode}")
	@ApiOperation(value = "좋아요 누른 유저들 보기", notes = "입력한 email 유저를 팔로우하는 from_email 리스트")
	public ResponseEntity<BasicResponse> ShowLikeList(@PathVariable("feedcode") Integer feedcode) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		result.object = feedService.ShowLikeList(feedcode);
		if (result.object != null) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 좋아요 리스트  조회 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
