package com.heycar.service;

import com.heycar.model.CarListing;
import com.heycar.model.CarSearchRequest;
import org.springframework.data.domain.Page;

public interface CarService {

    /**
     *
     * @param carListing
     * @return
     */
    public Long createCar(CarListing carListing);

    /**
     *
     * @param source
     */
    public void updateCar(CarListing source);

    /**
     *
     * @param searchParams
     * @param page
     * @param pageSize
     * @return
     */
    public Page<CarListing> searchCars(CarSearchRequest searchParams, int page, int pageSize);
}
