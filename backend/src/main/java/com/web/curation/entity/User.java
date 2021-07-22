package com.web.curation.entity;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;
 
@Entity	
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor	//롬복어노테이션
@NoArgsConstructor
public class User {
 
   @JsonIgnore
   @Id
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;	//자동증가하는 pk
 
   @Column(name = "username", length = 50, unique = true)
   private String username;
 
   @JsonIgnore
   @Column(name = "password", length = 100)
   private String password;
 
   @Column(name = "nickname", length = 50)
   private String nickname;
 
   @JsonIgnore
   @Column(name = "activated")
   private boolean activated;
 
   @ManyToMany
   @JoinTable(
      name = "user_authority",	//권한에 대한 관계
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
   private Set<Authority> authorities;
   
}