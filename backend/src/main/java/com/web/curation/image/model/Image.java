package com.web.curation.image.model;

import javax.persistence.*;

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
	
    private String imgname;
    private String imgpath;
    private long imgsize;

    @ManyToOne
    @JoinColumn(name = "feed_feedcode")
    private Feed feed;
    
    public Image(Integer feedcode, String imgname, String imgpath, long imgsize) {
		super();
		this.feedcode = feedcode;
		this.imgname = imgname;
		this.imgpath = imgpath;
		this.imgsize = imgsize;
	}
    
}

