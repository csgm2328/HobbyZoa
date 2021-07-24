package com.web.curation.follow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.exception.BadRequestException;
import com.web.curation.feed.model.Feed;
import com.web.curation.follow.model.Follow;
import com.web.curation.follow.repo.FollowRepo;
import com.web.curation.image.model.Image;
import com.web.curation.profile.model.ProfileImage;
import com.web.curation.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowRepo followRepo;
	@Autowired
	UserRepo userRepo;
	
	@Override
	//팔로우 & 언팔로우 기능: 변경사항은 프로필 보기 요청할때 적용
	public Follow Follow(String from, String to) throws BadRequestException{
		Optional<Follow> e = followRepo.findByFromemailAndToemail(from, to);
		if(e.isPresent()) {
			if(followRepo.deleteByFromemailAndToemail(from,to) != 0) {
				System.out.println("[" + from + "] 가 [" + to + "]를 더이상 팔로우하지 않음");
			}
			return null;
		}
		else {
			Follow followInfo = Follow.builder()
					.fromemail(from)
					.toemail(to).build();
			return  followRepo.save(followInfo);
		}
				
	}

	@Override
	//팔로우 여부 체크
	public boolean Check(String from, String to) {
		return followRepo.findByFromemailAndToemail(from, to).isPresent();
	}

	@Override
	//팔로워 목록 보기
	public List<String> ShowFollowerList(String email) {
		List<String> followers = new ArrayList<String>();
		followRepo.findAllByToemail(email).forEach(e -> followers.add(e.getFromemail()));
		return followers;
	}

	@Override
	//팔로잉 목록 보기
	public List<String> ShowFollowingList(String email) {
		List<String> followings = new ArrayList<String>();
		followRepo.findAllByFromemail(email).forEach(e -> followings.add(e.getToemail()));
		return followings;
	}

}
