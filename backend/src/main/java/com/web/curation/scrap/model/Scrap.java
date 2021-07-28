package com.web.curation.scrap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.web.curation.feed.model.Feed;

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
@Entity(name ="scrap")
public class Scrap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scrapcode;
	
	private Integer feedcode;
	private String email;
	
	@ManyToOne
	@JoinColumn(name="feed_feedcode")
	private Feed feed;
}
