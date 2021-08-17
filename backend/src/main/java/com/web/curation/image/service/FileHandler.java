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

import com.web.curation.feed.model.Feed;
import com.web.curation.image.model.Image;

import lombok.*;

@Service
@Transactional
@RequiredArgsConstructor
@Component
public class FileHandler {
	
	public List<Image> parseFileInfo(Feed feed,List<MultipartFile> multipartFiles) throws Exception{

        List<Image> fileList = new ArrayList<>(); //return할 리스트

        if(multipartFiles.isEmpty()){
            return fileList;
        }
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        String absolutePath = new File("").getAbsolutePath() + "/"; //리눅스 버전

        String path = "images/feeds/" + current_date;
        File file = new File(absolutePath+path);

        if(!file.exists()){
            file.mkdirs();
        }

        for (MultipartFile multipartFile : multipartFiles){
            
            if(!multipartFile.isEmpty()){
                String contentType = multipartFile.getContentType();
                String originalFileExtension;

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
                    else{
                        break;
                    }
                }

                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
                Image image = Image.builder()
                        .orgname(multipartFile.getOriginalFilename())
                        .newname(new_file_name)
                        .imgpath(path + "/" + new_file_name)
                        .imgsize(multipartFile.getSize())
                        .feed(feed)
                        .build();
                fileList.add(image);

                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }

        return fileList;
	}
}
