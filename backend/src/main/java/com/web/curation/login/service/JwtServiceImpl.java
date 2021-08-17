package com.web.curation.login.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.web.curation.common.error.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtServiceImpl implements JwtService {

	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

	private static final String TK = "secretToken";
	private static final int EXPIRE_MINUTES = 60;

	@Override
	public <T> String create(String key, T data, String subject) {
		String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES)) 
				.setSubject(subject).claim(key, data).signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
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
	public String get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(TK.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		String value = (String) claims.getBody().get("email");
		return value;
	}

	@Override
	public String getUserId() {
		return null;
	}
}
