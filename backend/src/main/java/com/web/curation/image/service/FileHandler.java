package com.web.curation.image.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.image.model.Image;

import lombok.*;

@Service
@Transactional
@RequiredArgsConstructor
@Component
public class FileHandler {
	
	public List<Image> parseFileInfo(Integer feedcode,List<MultipartFile> multipartFiles) throws Exception{

        List<Image> fileList = new ArrayList<>(); //return할 리스트

        if(multipartFiles.isEmpty()){
            return fileList;
        }
        
        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

//        String absolutePath = new File("").getAbsolutePath() + "\\";
        String absolutePath = new File("").getAbsolutePath() + "/"; //리눅스 버전

        String path = "images/feeds/" + current_date;
        File file = new File(absolutePath+path);
        // 저장할 위치의 디렉토리가 존재하지 않을 경우
        if(!file.exists()){
            file.mkdirs();
        }

        for (MultipartFile multipartFile : multipartFiles){
            
            if(!multipartFile.isEmpty()){
                // jpeg, png, gif 파일들만 받아서 처리할 예정
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                    // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
                if (ObjectUtils.isEmpty(contentType)){
                    break;
                }
                else{
                    if(contentType.contains("image/jpeg")){
                        originalFileExtension = ".jpg";
                    }
                    else if(contentType.contains("image/png")){
                        originalFileExtension = ".png";
                    }
                    else if(contentType.contains("image/gif")){
                        originalFileExtension = ".gif";
                    }
                    // 다른 파일 명이면 아무 일 하지 않는다
                    else{
                        break;
                    }
                }
                // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
                // 생성 후 리스트에 추가
                Image image = Image.builder()
                		.feedcode(feedcode)
                        .orgname(multipartFile.getOriginalFilename())
                        .newname(new_file_name)
                        .imgpath(path + "/" + new_file_name)
                        .imgsize(multipartFile.getSize())
                        .build();
                fileList.add(image);

                // 저장된 파일로 변경하여 이를 보여주기 위함
                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }

        return fileList;
	}
}
