package com.heycar.specification;

import com.heycar.model.CarListing;
import org.springframework.data.jpa.domain.Specification;

public class CarListingSpecification {

    public static Specification<CarListing> findByModel(Object model) {
        return (carListingRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(carListingRoot.get("model"), model);
    }

    public static Specification<CarListing> findByMake(Object make) {
        return (carListingRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(carListingRoot.get("make"), make);
    }

    public static Specification<CarListing> findByYear(Object year) {
        return (carListingRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(carListingRoot.get("year"), year);
    }

    public static Specification<CarListing> findByColour(Object colour) {
        return (carListingRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(carListingRoot.get("color"), colour);
    }


}
