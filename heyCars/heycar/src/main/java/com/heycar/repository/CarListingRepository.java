package com.heycar.repository;

import com.heycar.model.CarListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarListingRepository extends CrudRepository<CarListing, Long>,
        JpaSpecificationExecutor<CarListing> {
    Page<CarListing> findAll(Pageable pageable);
}
