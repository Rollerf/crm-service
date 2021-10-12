package com.agilemonkeys.crmservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
