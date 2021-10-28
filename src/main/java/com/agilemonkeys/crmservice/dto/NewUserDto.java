package com.agilemonkeys.crmservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDto {
    private Long userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    private Set<String> rolesName;
}
