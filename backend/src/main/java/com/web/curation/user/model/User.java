// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.web.curation.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //null아닌것만 responseBody에 포함
public class User {
    @Id
    private String email;
    @Column
    private String nickname;
    @Column
    private String password;
    @Column
    private String phone;
    @Column
    private String comment;
    @Column
    private boolean emailVerified;
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;
}
