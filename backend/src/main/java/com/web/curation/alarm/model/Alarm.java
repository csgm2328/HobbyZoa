package com.web.curation.alarm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity(name = "alarm")
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int alarmcode;
	@Column
	private String alarmType;
	
//	@ManyToOne
//	@JoinColumn(name = "email")
//	@JsonBackReference
//	private String from;
//	
//	@ManyToOne
//	@JoinColumn(name = "email")
//	@JsonBackReference
//	private String to;
	
	@Column(name = "from_email")
	private String fromemail;
	@Column(name = "to_email")
	private String toemail;
	@Column
	private String content;
	
    @Column(insertable = false, updatable = false)
    private LocalDateTime createDate;
	@Column
	private boolean alarmCheck;
}
