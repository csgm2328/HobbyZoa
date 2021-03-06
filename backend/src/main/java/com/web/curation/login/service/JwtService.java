package com.web.curation.login.service;


public interface JwtService {

	<T> String create(String key, T data, String subject);

	String get(String key);

	String getUserId();

	boolean isUsable(String jwt);

}
