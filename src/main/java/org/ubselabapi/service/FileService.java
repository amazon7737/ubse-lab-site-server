package org.ubselabapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public Long uploadFile(MultipartFile file) throws IOException {

        UploadFile attachFile = fileStore.storeFile(file);

        Long id = saveUrl(attachFile);

        return id;
    }

    @Transactional
    public Long saveUrl(UploadFile file) {

        //http://146.56.109.210:8000/display?filename=b93e6344-4db9-43bb-9fd8-1a595308026e.jpg
        Image image = Image.builder()
                .url( "http://146.56.109.210:8000/display?filename="+file.getStoreFileName())
                .build();

         Long id = imageRepository.save(image).getId();
         return id;


    }

    @Transactional
    public void updateFileAndupdateUrl(Long id, MultipartFile file) throws IOException {

        UploadFile attachFile = fileStore.storeFile(file);


        Image image = imageRepository.findById(id).get();

        image.updateUrl("http://146.56.109.210:8000/display?filename="+attachFile.getStoreFileName());

    }




    public Image findById(Long Id){
        return imageRepository.findById(Id).get();
    }

    public Image findByImageUrl(String url){
        return imageRepository.findByImageUrl(url);
    }


    @Transactional
    public void deleteImage(Long imageId){
        imageRepository.deleteById(imageId);
    }



}
