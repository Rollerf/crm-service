package com.agilemonkeys.crmservice.entity;

import lombok.Data;

@Data
public class AmazonCredentials {
    private String endpointUrl;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
