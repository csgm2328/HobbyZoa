package com.web.curation.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.web.curation.common.error.UnauthorizedException;
import com.web.curation.login.service.JwtService;

//Controller에 도달하기 전에 요청을 가로채 header에 포함된 토큰의 내용을 디코딩하여 
//그 내용을 다시 요청으로 담아 controller에 전달 
@Component
public class JwtInterceptor implements HandlerInterceptor {

	private static final String HEADER_AUTH = "auth-token";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// swagger는 interceptor무시
		String url = request.getRequestURI();
		if (url.contains("swagger") || url.contains("api-docs") || url.contains("webjars")) {
			return true;
		}

		final String token = request.getHeader(HEADER_AUTH);

		if (token != null && jwtService.isUsable(token)) {
			System.out.println("interceptor: " + jwtService.isUsable(token));
			return true;
		} else {
			System.out.println("토큰사용불가능");
			throw new UnauthorizedException();
		}

	}
}
