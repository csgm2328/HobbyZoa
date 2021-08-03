package com.web.curation.email.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailToken  {

    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;	//토큰 만료 시간

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private boolean expired;

    @Column
    private String userEmail;
    
	@CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;
    
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    /**
     * 이메일 인증 토큰 생성
     * @param userId
     * @return
     */
    @Transactional
    public static EmailToken createEmailConfirmationToken(String userEmail){
        EmailToken confirmationToken = new EmailToken();
//        confirmationToken.id = UUID.randomUUID().toString();
        confirmationToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분후 만료
//        System.out.println(confirmationToken.expirationDate); 
        confirmationToken.userEmail = userEmail;
//        System.out.println(confirmationToken.id);
        confirmationToken.expired = false;
//        confirmationToken.lastModifiedDate = LocalDateTime.now();
        return confirmationToken;
    }

    /**
     * 토큰 사용으로 인한 만료
     */
    public void useToken(){
        expired = true;
    }
    public String getId() {
		return id;
	}
}