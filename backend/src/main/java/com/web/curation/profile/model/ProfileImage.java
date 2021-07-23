package com.web.curation.profile.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ProfileImage {
	@Id
	private String email; //이메일당 프로필이미지 하나
	@Column
	private String image_name;
	@Column
	private String content_type;
	@Lob
	@Column
	private byte[] image_data;
	@Column
	private long image_size;
	
}
