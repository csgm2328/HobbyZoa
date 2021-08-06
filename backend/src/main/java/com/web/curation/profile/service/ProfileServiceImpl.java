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
	ProfileImageHandler profileHandler;

	@Override
	// 프로필 이미지 얻기
	public Optional<ProfileImage> findProfileImageById(String email) {
		return profileImageRepo.findById(email);
	}

	@Override
	// 프로필 이미지 저장 or 수정
	public ProfileImage saveImage(String email, MultipartFile file) throws IllegalStateException, IOException {
		return profileImageRepo.save(profileHandler.parseFileInfo(email, file));
	}

	@Override
	//프로필 정보 얻기: 회원 가입시 자동 생성하고 보기 요청마다 팔로워, 피드수를 계속 업데이트해야므로 save() 동작
	public Profile findProfileById(String email) {
		Optional<ProfileImage> e =profileImageRepo.findById(email);
		return profileRepo.save(Profile.builder()
					.email(email)
					.following(followRepo.countByFromemail(email))
					.follower(followRepo.countByToemail(email))
					.feeds(feedRepo.countByEmail(email))
					.imgpath(e.isPresent() ? e.get().getImgpath() : null)
					.comment(userRepo.findById(email).get().getComment())
					.build());
	}

}
