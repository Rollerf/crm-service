package com.agilemonkeys.crmservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Lob
    private Byte[] profilePicture;
    private Long createdBy;
    private Long updatedBy;
}
