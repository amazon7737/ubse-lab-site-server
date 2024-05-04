package org.ubselabapi.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.ubselabapi.domain.UploadFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * https://keeeeeepgoing.tistory.com/221
 */
@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) { //파일이름을 받아서 FullPath를 만들어주는 메소드
        return fileDir + filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException { //여러개의 이미지를 한번에 저장하기 위한 메소드
        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }

        return storeFileResult;
    }


    public UploadFile storeFile(MultipartFile multipartFile) throws IOException { // 파일을 서버에 저장하는 메소드
        // MultipartFile로 받은후 서버에 저장하고, UploadFile 객체를 만들어 반환
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename(); //파일의 이름가져온다 (확장자가 포함되어있다.) ex) dog.png

        String storeFileName = createStoreFileName(originalFilename);  //서버에 저장될 파일명 만들기

        multipartFile.transferTo(new File(getFullPath(storeFileName))); // 파일을 서버에 저장한다 , getFullPath메소드를 이용해서 PullPath를 만들어줌
        //File은 객체를 생성할때 Path를 파라미터로 받는다.

        return new UploadFile(originalFilename, storeFileName);
    }

    private static String createStoreFileName(String originalFilename) {//서버에 저장될 파일명 만드는 메소드

        //서버에 저장될 파일명은 고유해야하므로 UUID를 붙여서 만들어준다.
        String uuid = UUID.randomUUID().toString();

        //확장자는 붙여줘야 구분하기 편하니까 originalFilename에서 확장자를 가져온다.
        String ext = extractExt(originalFilename);

        // uuid + "." + 확장자
        return uuid + "." + ext; // 서버에 저장될 파일이름 생성
    }

    private static String extractExt(String originalFilename) { //파일이름에서 확장자 부분만 반환하는 메소드

        int index = originalFilename.lastIndexOf("."); // 파일이름중 마지막 .의 인덱스를 가져온다.

        return originalFilename.substring(index + 1); // . 위치 뒷부분을 subString으로 가져온다.
    }


}