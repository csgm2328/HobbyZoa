package com.web.curation.hobby.model;

import java.util.List;

import javax.persistence.*;

import com.web.curation.badge.model.Badge;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name ="hobby")
public class Hobby {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hobbycode;
	
	private String name;
	
	private String email; //(fk)
	
	@OneToMany(mappedBy = "hobby", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Badge> badges;
}
