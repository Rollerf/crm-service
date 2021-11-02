package com.agilemonkeys.crmservice.security.entity;

import com.agilemonkeys.crmservice.security.enums.RoleName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
