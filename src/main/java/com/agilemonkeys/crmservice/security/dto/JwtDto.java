package com.agilemonkeys.crmservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {
    private String token;
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;
}
