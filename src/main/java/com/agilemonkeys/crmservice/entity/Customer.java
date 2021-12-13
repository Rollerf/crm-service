package com.agilemonkeys.crmservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private Long createdBy;
    private Long updatedBy;
    private String photoUrl;
}
