package com.heycar.controller;

import com.heycar.model.CarListing;
import com.heycar.model.CarSearchRequest;
import com.heycar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/search")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public Page<CarListing> search(@Valid @ModelAttribute CarSearchRequest carSearchRequest,
                                   @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {

        return carService.searchCars(carSearchRequest, page, pageSize);


    }
}
