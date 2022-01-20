package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.AmazonCredentials;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ReadJsonService {
    @Value("${amazon.credentials}")
    private String amazonCredentialsFilePath;

    public AmazonCredentials readAmazonCredentials() throws IOException {
        InputStream in = ReadJsonService.class.getResourceAsStream(amazonCredentialsFilePath);
        String credentials;

        if(in == null){
            throw new NotFoundException("Amazon credentials not found");
        }

        credentials = new String(in.readAllBytes());


        return new ObjectMapper().readValue(credentials, AmazonCredentials.class);
    }
}
