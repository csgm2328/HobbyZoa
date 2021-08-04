package com.web.curation.badge.model;

import javax.persistence.*;

import com.web.curation.hobby.model.Hobby;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name ="badge")
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int badgecode;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "hobbycode")
	private Hobby hobby;
}
