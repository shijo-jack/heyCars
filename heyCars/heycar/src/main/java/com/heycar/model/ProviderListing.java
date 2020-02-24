package com.heycar.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class ProviderListing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private long providerId;

    @OneToOne
    private CarListing carListing;

    @Column
    private String listingId;

    public ProviderListing(long providerId, CarListing carListing, String listingId) {
        this.carListing = carListing;
        this.listingId = listingId;
        this.providerId = providerId;
    }

}
