package com.web.curation.follow.model;

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

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int followcode;

	@Column(name = "from_email")
	String fromemail;
	@Column(name = "to_email")
	String toemail;

	public Follow(String fromemail, String toemail) {
		super();
		this.fromemail = fromemail;
		this.fromemail = toemail;
	}
}
