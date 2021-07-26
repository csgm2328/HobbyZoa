package com.web.curation.login.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.web.curation.common.error.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtServiceImpl implements JwtService {

	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

	private static final String TK = "secretToken";
	private static final int EXPIRE_MINUTES = 60;

	// 토큰 생성 key:"email", data:getEmail, "access-token"
	@Override
	public <T> String create(String key, T data, String subject) {
		String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES)) // 토큰 유효시간
				.setSubject(subject).claim(key, data).signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
		// 토큰 signature설정(알고리즘과 시크릿키 필요)
		return jwt;
	}

	private byte[] generateKey() {
		byte[] key = null;

		try {
			key = TK.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("jwt key error :::", e.getMessage());
			}
			e.printStackTrace();
		}

		return key;
	}

	// 전달 받은 토큰이 제대로 생성된 것 인지 확인
	// claim -> jwt payload 내용 중 하나, name:value 한 쌍
	@Override
	public boolean isUsable(String jwt) {

		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Map<String, Object> get(String key) {
		// 디코딩
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;

		try {
			claims = Jwts.parser().setSigningKey(TK.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {

			throw new UnauthorizedException();
		}
//		 Map<String, Object> value = (LinkedHashMap<String,
//		 Object>)claims.getBody().get(key);		 
		Map<String, Object> value = claims.getBody();

		return value;
	}

//	@Override
//	public String getUserId() {
//
//		return (String) this.get("user").get("userid");
//	}

}
