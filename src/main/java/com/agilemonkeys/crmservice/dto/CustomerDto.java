package com.agilemonkeys.crmservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Long customerId;
    private String name;
    private String surname;
    private Byte[] profilePicture;
    private Long createdBy;
    private Long updatedBy;
}
