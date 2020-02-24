package com.heycar.service;

import com.heycar.constant.HeyCarConstants;
import com.heycar.model.CarListing;
import com.heycar.model.CarSearchRequest;
import com.heycar.repository.CarListingRepository;
import com.heycar.specification.CarListingSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
        private CarListingRepository repository;


        @Transactional
        public Long createCar(CarListing carListing) {
            CarListing saved = repository.save(carListing);

            return saved.getId();
        }

        @Transactional
        public void updateCar(CarListing source) {
            Optional<CarListing> optionalExisting = repository.findById(source.getId());
            if (optionalExisting.isPresent()) {
                CarListing existing = optionalExisting.get();
                existing.setColor(source.getColor());
                existing.setKW(source.getKW());
                existing.setMake(source.getMake());
                existing.setModel(source.getModel());
                existing.setPrice(source.getPrice());
            } else {
                throw new EntityNotFoundException("Car not found");
            }
        }

        /**
         *
         * @param carSearchRequest
         * @param page
         * @param pageSize
         * @return
         */
        @Transactional
        public Page<CarListing> searchCars(CarSearchRequest carSearchRequest, int page, int pageSize) {
            Map<String, Object> searchParams = getSearchCriteriaInMap(carSearchRequest);

            if (searchParams.isEmpty()) {
                return repository.findAll(PageRequest.of(page, pageSize));
            }

            Specification<CarListing> spec = null;
            if (searchParams.containsKey(HeyCarConstants.MAKE)) {
                Specification<CarListing> byParam = CarListingSpecification
                        .findByMake(searchParams.get(HeyCarConstants.MAKE));
                spec = spec != null ? spec.and(byParam) : byParam;
            }
            if (searchParams.containsKey(HeyCarConstants.MODEL)) {
                Specification<CarListing> byParam = CarListingSpecification
                        .findByModel(searchParams.get(HeyCarConstants.MODEL));
                spec = spec != null ? spec.and(byParam) : byParam;
            }
            if (searchParams.containsKey(HeyCarConstants.YEAR)) {
                Specification<CarListing> byParam = CarListingSpecification
                        .findByYear(searchParams.get(HeyCarConstants.YEAR));
                spec = spec != null ? spec.and(byParam) : byParam;
            }
            if (searchParams.containsKey(HeyCarConstants.COLOR)) {
                Specification<CarListing> byParam = CarListingSpecification
                        .findByColour(searchParams.get(HeyCarConstants.COLOR));
                spec = spec != null ? spec.and(byParam) : byParam;
            }

            return spec != null ? repository.findAll(spec, PageRequest.of(page, pageSize))
                    : repository.findAll(PageRequest.of(page, pageSize));
        }

    /**
     *
     * @param carSearchRequest
     * @return
     */
    private Map<String, Object> getSearchCriteriaInMap(CarSearchRequest carSearchRequest) {
        Map<String, Object> searchParams = new HashMap<>();
        Optional.ofNullable(carSearchRequest.getMake()).ifPresent(m -> searchParams.put(HeyCarConstants.MAKE, m));
        Optional.ofNullable(carSearchRequest.getModel()).ifPresent(m -> searchParams.put(HeyCarConstants.MODEL, m));
        Optional.ofNullable(carSearchRequest.getYear()).ifPresent(m -> searchParams.put(HeyCarConstants.YEAR, m));
        Optional.ofNullable(carSearchRequest.getColor()).ifPresent(m -> searchParams.put(HeyCarConstants.COLOR, m));
        return searchParams;
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<CarListing> get(long id) {
            return repository.findById(id);
        }
}

