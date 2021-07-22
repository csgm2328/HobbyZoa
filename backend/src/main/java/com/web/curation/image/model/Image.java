package com.web.curation.image.model;

import javax.persistence.*;

import com.sun.istack.NotNull;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image {
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer imgcode;
	
	@NotNull
    private Integer feedcode; //fk
	
    private String imgname;
    private String imgpath;
    private long imgsize;

    public Image(Integer feedcode, String imgname, String imgpath, long imgsize) {
		super();
		this.feedcode = feedcode;
		this.imgname = imgname;
		this.imgpath = imgpath;
		this.imgsize = imgsize;
	}

	public Integer getImgcode() {
		return imgcode;
	}

	public void setImgcode(Integer imgcode) {
		this.imgcode = imgcode;
	}

	public Integer getFeedcode() {
		return feedcode;
	}

	public void setFeedcode(Integer feedcode) {
		this.feedcode = feedcode;
	}
	
	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public long getImgsize() {
		return imgsize;
	}

	public void setImgsize(long imgsize) {
		this.imgsize = imgsize;
	}
    
}

