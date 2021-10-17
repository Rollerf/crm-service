package com.agilemonkeys.crmservice.entity;

import lombok.*;

import javax.persistence.*;

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
    private byte[] contentBytes;
    private Long size;
    private String contentType;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
