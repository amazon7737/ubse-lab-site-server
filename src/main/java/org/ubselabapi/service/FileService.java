package org.ubselabapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.ubselabapi.domain.Image;
import org.ubselabapi.domain.UploadFile;
import org.ubselabapi.repository.ImageRepository;
import org.ubselabapi.util.FileStore;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final FileStore fileStore;

    private final ImageRepository imageRepository;

    public Long uploadFile(MultipartFile file) throws IOException {

        UploadFile attachFile = fileStore.storeFile(file);

        Long id = saveUrl(attachFile);

        return id;
    }

    public Long saveUrl(UploadFile file) {

        Image image = Image.builder()
                .url(file.getStoreFileName())
                .build();

         imageRepository.save(image);
         return imageRepository.findByImageUrl(file.getStoreFileName()).getId();


    }

    public Image findById(Long Id){
        return imageRepository.findById(Id).get();
    }

    public Image findByImageUrl(String url){
        return imageRepository.findByImageUrl(url);
    }

    public void deleteImage(Long imageId){
        imageRepository.deleteById(imageId);
    }



}
