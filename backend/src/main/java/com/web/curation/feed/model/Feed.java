package com.web.curation.feed.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.web.curation.image.model.Image;
import com.web.curation.reply.model.Reply;
import com.web.curation.tag.model.Feedtags;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name ="feed")
public class Feed {

	
	private String email; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int feedcode; 

	private String nickname; 
	
	@Column(insertable=false, updatable=false)
	private LocalDateTime regtime; 
	
	private String comment; 
	private Integer likes; 
	private Integer scrap; 
	
	
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Image> images;
	
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Reply> replies;
	
	@OneToMany(mappedBy = "feed", orphanRemoval = true)
	@JsonManagedReference
	private List<Feedtags> feedtags;	
	
	@PrePersist
    public void prePersist() {
        this.likes = this.likes == null ? 0 : this.likes;
        this.scrap = this.scrap == null ? 0 : this.scrap;
    }

}
