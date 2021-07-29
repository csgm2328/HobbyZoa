package com.web.curation.find.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import com.web.curation.feed.model.Feed;
import com.web.curation.image.model.Image;
import com.web.curation.user.model.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "saveuser")
public class SaveUser {

	@Id
	@Column(name = "email")
	private String saveEmail;

	@Column(name = "nickname")
	private String saveNickname;

	public String getSaveEmail() {
		return saveEmail;
	}

	public void setSaveEmail(String saveEmail) {
		this.saveEmail = saveEmail;
	}

	public String getSaveNickname() {
		return saveNickname;
	}

	public void setSaveNickname(String saveNickname) {
		this.saveNickname = saveNickname;
	}

}
