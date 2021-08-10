package com.web.curation.attendance.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.web.curation.hobby.model.Hobby;

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
@Entity(name ="attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int checkcode;
	
	private String email;
	
	@ManyToOne(targetEntity=Hobby.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "hobbycode")
	@JsonBackReference
	private Hobby hobby;
	
	@Column(insertable=false, updatable=false)
	private LocalDateTime regtime;
	
	private int start;
	private int end;
	private String comment;
}
