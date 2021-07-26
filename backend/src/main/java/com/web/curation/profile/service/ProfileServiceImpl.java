package com.web.curation.profile.service;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.repo.FeedRepo;
import com.web.curation.follow.repo.FollowRepo;
import com.web.curation.profile.model.Profile;
import com.web.curation.profile.model.ProfileImage;
import com.web.curation.profile.repo.ProfileImageRepo;
import com.web.curation.profile.repo.ProfileRepo;
import com.web.curation.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	ProfileRepo profileRepo;
	@Autowired
	ProfileImageRepo profileImageRepo;
	@Autowired
	FollowRepo followRepo;
	@Autowired
	FeedRepo feedRepo;
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProfileHandler profileHandler;
	
	@Override
	//프로필 이미지 얻기
	public Optional<ProfileImage> findProfileImageById(String email) {
		return profileImageRepo.findById(email);
	}

	@Override
	//프로필 이미지 저장
	public ProfileImage save(String email, MultipartFile file) throws IllegalStateException, IOException {
		return profileImageRepo.save(profileHandler.parseFileInfo(email, file));
	}

	@Override
	//프로필 정보 얻기: 처음 이면 생성, 아니면 수정됨
	public Profile findProfileById(String email) {
		return profileRepo.save(Profile.builder()
					.email(email)
					.following(followRepo.countByFromemail(email))
					.follower(followRepo.countByToemail(email))
					.feeds(feedRepo.countByEmail(email))
					.imgpath(profileImageRepo.findById(email).get().getImgpath())
					.comment(userRepo.findById(email).get().getComment())
					.build());
	}

}
