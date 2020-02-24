package com.heycar.repository;

import com.heycar.model.ProviderListing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderListingRepository extends CrudRepository<ProviderListing, Long> {

    Optional<ProviderListing> findByProviderIdAndListingId(long providerId, String listingId);
}
