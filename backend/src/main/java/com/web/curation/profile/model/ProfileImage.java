package com.web.curation.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProfileImage {
	@Id
	private String email; //이메일당 프로필이미지 하나
	@Column
	private String imgname;
	@Column
	private String content_type;
//	@Lob
//	@Column
//	private byte[] imgdata;
	@Column
	private long imgsize;
	@Column
	private String imgpath;
	
	public ProfileImage(String email, String image_name, String content_type, long image_size,
			String image_path) {
		super();
		this.email = email;
		this.imgname = image_name;
		this.content_type = content_type;
		this.imgsize = image_size;
		this.imgpath = image_path;
	}
}
