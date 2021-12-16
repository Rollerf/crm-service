package com.agilemonkeys.crmservice.entity;

import com.agilemonkeys.crmservice.audition.entity.Auditable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String photoUrl;
}
