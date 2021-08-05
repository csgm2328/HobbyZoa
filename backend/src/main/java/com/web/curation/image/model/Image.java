package com.web.curation.image.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import com.web.curation.feed.model.Feed;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imgcode;
	
	@NotNull
    private Integer feedcode; //fk
	
    private String orgname;
    private String newname;
    private String imgpath;
    private long imgsize;

    @ManyToOne
    @JoinColumn(name = "feed_feedcode", insertable=false, updatable=false)
    @JsonBackReference
    private Feed feed;
    
    public Image(Integer feedcode, String orgname, String newname, String imgpath, long imgsize) {
		super();
		this.feedcode = feedcode;
		this.orgname = orgname;
		this.newname = newname;
		this.imgpath = imgpath;
		this.imgsize = imgsize;
	}
    
}

