package com.web.curation.profile.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.profile.model.ProfileImage;

import lombok.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileHandler{
	public ProfileImage parseFileInfo(String email, MultipartFile uploadFile) throws IllegalStateException, IOException{
		if (uploadFile.isEmpty()) {
//        	System.out.println("파일 비었음");
			return null;
		}

		// 파일 이름을 업로드 한 날짜로 바꾸어서 저장
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String current_date = simpleDateFormat.format(new Date());

		// 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
//		String absolutePath = new File("").getAbsolutePath() + "\\";
		String absolutePath = "C:\\subpjt2Img\\";
		String path = "images/profiles";
		File file = new File(absolutePath + path);
		// 저장할 위치의 디렉토리가 존재하지 않을 경우
		if (!file.exists()) {
			file.mkdirs();
		}

		// jpeg, png, gif 파일들만 받아서 처리할 예정
		String contentType = uploadFile.getContentType();
		String originalFileExtension;
		// 확장자 명이 없으면 이 파일은 잘 못 된 것이다
		if (ObjectUtils.isEmpty(contentType)) {
			return null;
		} else {
			if (contentType.contains("image/jpeg")) {
				originalFileExtension = ".jpg";
			} else if (contentType.contains("image/png")) {
				originalFileExtension = ".png";
			} else if (contentType.contains("image/gif")) {
				originalFileExtension = ".gif";
			}
			// 다른 파일 명이면 아무 일 하지 않는다
			else {
				return null;
			}
		}
		// 파일이름 : 유저이메일( PK ) : 날짜 .확장자
		String new_file_name = email + "_" + current_date + originalFileExtension;
		// 생성 후 리스트에 추가
		System.out.printf("업로드한 파일 크기: %.2fKB", uploadFile.getSize() / Math.pow(2,10)); //KB
		ProfileImage image = ProfileImage.builder()
				.email(email)
				.imgname(uploadFile.getOriginalFilename())
				.content_type(contentType)
				.imgsize(uploadFile.getSize())
				.imgpath(absolutePath  + path + "/" + new_file_name).build();

		// 저장된 파일로 변경하여 이를 보여주기 위함
		// Update: 폴더 내 말고 절대경로로 변경
		file = new File(absolutePath + path + "/" + new_file_name);
		uploadFile.transferTo(file);

		return image;
	}
}
