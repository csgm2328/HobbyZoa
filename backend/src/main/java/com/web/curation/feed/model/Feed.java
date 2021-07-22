package com.web.curation.feed.model;

import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name ="feed")
public class Feed {

	
	private String email; //이메일주소
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스 AI에 위임
	private int feedcode; //피드구별번호, 기본키
	//Statement.getGeneratedKeys() 를 사용해서 데이터를 저장함과 동시에 생성된 기본 키 값을 얻어 올 수 있음.

	private String nickname; //닉네임
	
	@Column(insertable=false, updatable=false)
	private LocalDateTime regtime; //등록일, 날짜 포맷
	
	private String comment; //한줄설명
	private String metadata; //사진 등 메타데이터, 타입 설정
	
	private Integer likes; //좋아요 수
	private Integer scrap; //스크랩 수
	
	//기본 생성자
	public Feed() {	}
	
	//likes, scrap 없는 생성자
	public Feed(String email, int feedcode, String nickname, String comment, String metadata) {
		super();
		this.email = email;
		this.feedcode = feedcode;
		this.nickname = nickname;
		this.comment = comment;
		this.metadata = metadata;
	}

	//feedcode, regtime 없는 생성자
	public Feed(String email, String nickname, String comment, String metadata, int likes, int scrap) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.comment = comment;
		this.metadata = metadata;
		this.likes = likes;
		this.scrap = scrap;
	}
	
	
//	@PrePersist
//    public void regtime() {
//        this.regtime = LocalDateTime.now();
//    }

	//default 0이거나 원래 값 넣어주기
	@PrePersist
    public void prePersist() {
        this.likes = this.likes == null ? 0 : this.likes;
        this.scrap = this.scrap == null ? 0 : this.scrap;
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


	public LocalDateTime getRegtime() {
		return regtime;
	}


	public void setRegtime(LocalDateTime regtime) {
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
