package com.web.curation.feed.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.web.curation.image.model.Image;
import com.web.curation.reply.model.Reply;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name ="feed")
public class Feed {

	
	private String email; //이메일주소
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스 AI에 위임
	private int feedcode; //피드구별번호, 기본키

	private String nickname; //닉네임
	
	@Column(insertable=false, updatable=false)
	private LocalDateTime regtime; //등록일, 날짜 포맷
	
	private String comment; //한줄설명
	private Integer likes; //좋아요 수
	private Integer scrap; //스크랩 수
	
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> images;
	
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reply> replies;
	
	//likes, scrap 없는 생성자
	public Feed(String email, int feedcode, String nickname, LocalDateTime regtime, String comment) {
		super();
		this.email = email;
		this.feedcode = feedcode;
		this.nickname = nickname;
		this.regtime = regtime;
		this.comment = comment;
	}

	//feedcode, regtime 없는 생성자
	public Feed(String email, String nickname, String comment, int likes, int scrap) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.comment = comment;
		this.likes = likes;
		this.scrap = scrap;
	}
	
	//default 0이거나 원래 값 넣어주기
	@PrePersist
    public void prePersist() {
        this.likes = this.likes == null ? 0 : this.likes;
        this.scrap = this.scrap == null ? 0 : this.scrap;
    }
	//image연결 함수
	public void addImage(Image image) {
	    images.add(image);
	    image.setFeed(this);
	}

	public void removeImage(Image image) {
	    images.remove(image);
	    image.setFeed(null);
	}
	//reply연결 함수
	public void addReply(Reply reply) {
	    replies.add(reply);
	    reply.setFeed(this);
	}

	public void removeReply(Reply reply) {
		replies.remove(reply);
	    reply.setFeed(null);
	}
}
