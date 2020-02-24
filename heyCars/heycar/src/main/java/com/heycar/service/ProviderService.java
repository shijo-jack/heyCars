package com.heycar.service;

import com.heycar.model.ProviderListing;

import java.util.List;

public interface ProviderService {

    /**
     *
     * @param uploadList
     */
    public void saveOrUpdate(List<ProviderListing> uploadList);
}
