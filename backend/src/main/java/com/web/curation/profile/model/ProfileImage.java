package com.web.curation.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProfileImage {
	@Id
	private String email;
	@Column
	private String imgname;
	@Column
	private String content_type;
	@Column
	private long imgsize;
	@Column
	private String imgpath;
	
}
