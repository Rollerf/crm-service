package com.agilemonkeys.crmservice.repository;

import com.agilemonkeys.crmservice.entity.AmazonCredentials;
import com.agilemonkeys.crmservice.service.ReadJsonService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class PhotoRepositoryImpl implements PhotoRepository {

    private AmazonS3 s3Client;

    private AmazonCredentials amazonCredentials;

    @Autowired
    ReadJsonService readJsonService;

    @PostConstruct
    private void initializeAmazon() throws IOException {
        amazonCredentials = readJsonService.readAmazonCredentials();

        BasicAWSCredentials credentials = new BasicAWSCredentials(amazonCredentials.getAccessKey(), amazonCredentials.getSecretKey());
        s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_3)
                .build();
    }

    @Override
    public String uploadPhoto(MultipartFile multipartFile, Long customerId) throws IOException {
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String fileName = "photo_customer_" + customerId + extension;
        String photoUrl = amazonCredentials.getEndpointUrl() + "/" + amazonCredentials.getBucketName() + "/" + fileName;

        uploadPhotoToS3(multipartFile, fileName);

        return photoUrl;
    }

    @Override
    public void deletePhoto(String photoUrl) {
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);

        try {
            s3Client.deleteObject(new DeleteObjectRequest(amazonCredentials.getBucketName(), fileName));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void uploadPhotoToS3(MultipartFile multipartFile, String fileName) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        s3Client.putObject(new PutObjectRequest(amazonCredentials.getBucketName(), fileName, multipartFile.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}
