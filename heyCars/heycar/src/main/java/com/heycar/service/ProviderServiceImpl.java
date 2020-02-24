package com.heycar.service;

import com.heycar.model.CarListing;
import com.heycar.model.ProviderListing;
import com.heycar.repository.ProviderListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderListingRepository providerListingRepository;

    @Autowired
    private CarService carService;

    @Override
    public void saveOrUpdate(List<ProviderListing> uploadList) {

        uploadList.forEach(carListing -> {
                    if (!providerListingRepository.findByProviderIdAndListingId(carListing.getProviderId(), carListing.getListingId())
                            .isPresent()) {
                        create(carListing);
                    } else {
                        update(carListing);
                    }
                }
        );

    }

    @Transactional
    private long create(ProviderListing carListing) {
        carService.createCar(carListing.getCarListing());
        return providerListingRepository.save(carListing).getId();
    }

    @Transactional
    private void update(ProviderListing source) {
        Optional<ProviderListing> existing = providerListingRepository
                .findByProviderIdAndListingId(source.getProviderId(), source.getListingId());
        if (existing.isPresent()) {
            ProviderListing providerCarListing = existing.get();
            CarListing sourceCar = source.getCarListing();
            CarListing targetListing = providerCarListing.getCarListing();

            targetListing.setPrice(sourceCar.getPrice());
            targetListing.setModel(sourceCar.getModel());
            targetListing.setMake(sourceCar.getMake());
            targetListing.setKW(sourceCar.getKW());
            targetListing.setColor(sourceCar.getColor());
            targetListing.setYear(sourceCar.getYear());
            providerListingRepository.save(providerCarListing);
        }
    }
}
