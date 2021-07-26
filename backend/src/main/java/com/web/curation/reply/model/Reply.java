package com.web.curation.reply.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name ="reply")
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int replycode;
	
	private String email;
	private String nickname;
	private String content;
	private Boolean hide;
	
	@Column(insertable=false, updatable=false)
	private LocalDateTime regtime; //등록일, 날짜 포맷
	
	private int feedcode;
	
	
}