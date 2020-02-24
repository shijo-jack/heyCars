package com.heycar.controller;

import com.heycar.mapper.ProviderCarSpecificationMapper;
import com.heycar.model.ProviderCarSpecificationInJson;
import com.heycar.model.ProviderListing;
import com.heycar.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProviderCarSpecificationMapper providerCarSpecificationMapper;

    @PostMapping(value = "/upload_csv/{providerId}/")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendProviderInfoViaCSV(@PathVariable(required = true) Long providerId,
            @RequestParam("file") MultipartFile csv) throws IOException {
        providerService.saveOrUpdate(providerCarSpecificationMapper.map(csv, providerId));
    }

    @PostMapping(value = "/vehicle_listings/{providerId}/")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendProviderInfoViaJson(@Valid @RequestBody List<ProviderCarSpecificationInJson> providerCarSpecificationInJsonList,
                       @PathVariable(required = true) Long providerId) {
        providerService.saveOrUpdate(providerCarSpecificationMapper.map(providerCarSpecificationInJsonList, providerId));
    }

}
