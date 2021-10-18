package com.agilemonkeys.crmservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Photo{
    @Id
    private Long customerId;
    @Lob
    @NotNull
    private byte[] contentBytes;
    @NotNull
    private Long size;
    @NotNull
    private String contentType;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
