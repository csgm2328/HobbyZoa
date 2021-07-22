package com.web.curation.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity	//기본적인 web보안을 활성화 하겠다는 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(WebSecurity web) {	//h2-console/하위, /favicon.ico대한 요청은 security무시
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                );
    }
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()	//HttpServletRequest를 사용 요청에 대한 접근 제한 설정하겠다.
	                .antMatchers("/api/hello").permitAll()	//이거는 인증없이 허용
	                .anyRequest().authenticated();	//나머지는 전부 인증
	    }
	 

}
