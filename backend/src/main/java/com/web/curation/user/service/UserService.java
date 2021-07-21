package com.web.curation.user.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.web.curation.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public interface UserService {
	Optional<User> findById(String email);
	User save(User user);
	Optional<User> findUserByEmailAndPassword(String email, String password);
}
