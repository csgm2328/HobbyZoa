package com.web.curation.feed.model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name ="feed")
public class Feed {

	
	private String email; //이메일주소
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedcode; //피드구별번호, 기본키
	
	private String nickname; //닉네임
	private String regtime; //등록일, 날짜 포맷 설정해야함
	private String comment; //한줄설명
	private String metadata; //사진 등 메타데이터, 타입 설정
	private int likes; //좋아요 수
	private int scrap; //스크랩 수
	
	//regtime 없는 생성자
	public Feed(String email, String nickname, String comment, String metadata, int likes, int scrap) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.comment = comment;
		this.metadata = metadata;
		this.likes = likes;
		this.scrap = scrap;
	}
	
	
	//regtime 있는 생성자
	public Feed(String email, String nickname, String regtime, String comment, String metadata, int likes, int scrap) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.regtime = regtime;
		this.comment = comment;
		this.metadata = metadata;
		this.likes = likes;
		this.scrap = scrap;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getFeedcode() {
		return feedcode;
	}


	public void setFeedcode(int feedcode) {
		this.feedcode = feedcode;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getRegtime() {
		return regtime;
	}


	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getMetadata() {
		return metadata;
	}


	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	public int getScrap() {
		return scrap;
	}


	public void setScrap(int scrap) {
		this.scrap = scrap;
	}
	
	
}
