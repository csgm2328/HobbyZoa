package com.web.curation.alarm.model;

import java.time.LocalDateTime;

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
	
	@Column(name = "from_email")
	private String fromemail;
	@Column(name = "to_email")
	private String toemail;
	@Column
	private int feedcode;
	@Column
	private String content;
	@Column
	private boolean alarmCheck;
	
    @Column(insertable=false, updatable=false)
	private LocalDateTime createDate; //알림 생성 날짜
    @Column
    private LocalDateTime checkDate; //알림 확인 날짜
    
	public boolean getAlarmCheck() {
		return alarmCheck;
	}
}
