package com.web.curation.image.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	
    private String orgname;
    private String newname;
    private String imgpath;
    private long imgsize;

    @ManyToOne
    @JoinColumn(name = "feedcode")
    @JsonBackReference
    private Feed feed;
    
}

