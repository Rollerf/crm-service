package com.agilemonkeys.crmservice.dto;

import com.agilemonkeys.crmservice.security.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserDto {
    private Long userId;
    @NotBlank
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<String> rolesName;
    private Set<Role> roles;
}
