package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.util.Constants;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    CustomerService customerService;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    private AmazonS3 s3Client;

    @PostConstruct
    private void initializeAmazon() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_3)
                .build();
    }

    private void uploadPhotoToS3(MultipartFile multipartFile, String fileName) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, multipartFile.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadPhoto(MultipartFile multipartFile, Long customerId, Long userId) throws NotFoundException {
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String fileName = "photo_customer_" + customerId + extension;
        String photoUrl = endpointUrl + "/" + bucketName + "/" + fileName;
        Customer customer = Customer.builder().customerId(customerId).photoUrl(photoUrl).updatedBy(userId).build();

        try {
            uploadPhotoToS3(multipartFile, fileName);
            customerService.updateCustomerById(customerId, customer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException(Constants.INVALID_PHOTO);
        }

        return photoUrl;
    }

    public String deletePhoto(Customer customer, Long userId) {
        if(customer == null || customer.getPhotoUrl() == null){
            throw new NotFoundException(Constants.INVALID_USER_ID);
        }

        String photoUrl = customer.getPhotoUrl();
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
        Customer customerSaved = Customer.builder().customerId(customer.getCustomerId()).photoUrl("").updatedBy(userId).build();

        s3Client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        customerService.updateCustomerById(customer.getCustomerId(), customerSaved);

        return "Photo deleted successfully";
    }

    public String deletePhoto(Customer customer) {
        if(customer == null || customer.getPhotoUrl() == null){
            throw new NotFoundException(Constants.INVALID_USER_ID);
        }

        String photoUrl = customer.getPhotoUrl();
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);

        s3Client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));

        return "Photo deleted successfully";
    }
}