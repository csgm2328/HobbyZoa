package com.web.curation.feed.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	private String tag; //태그
	
	
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Image> images;
	
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Reply> replies;
	
	//default 0이거나 원래 값 넣어주기
	@PrePersist
    public void prePersist() {
		this.tag = this.tag == null? null: this.tag;
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
