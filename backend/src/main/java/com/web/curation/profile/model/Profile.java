package com.web.curation.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
	@Id
	@Email
	String email;
	@Column
	String nickname;
	@Column
	int following;
	@Column
	int follower;
	@Column
	@ColumnDefault("0")
	int feeds;
	@Column
	String imgpath;
	@Column
	String comment;
}
