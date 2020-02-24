package com.heycar.mapper;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.heycar.model.CarListing;
import com.heycar.model.ProviderCarSpecificationInJson;
import com.heycar.model.ProviderCarSpecificationsInCSV;
import com.heycar.model.ProviderListing;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProviderCarSpecificationMapper {

    /**
     *
     * @param csv
     * @param providerId
     * @return
     * @throws IOException
     */
    public List<ProviderListing> map(MultipartFile csv, Long providerId) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        ObjectReader objectReader = csvMapper.readerFor(ProviderCarSpecificationsInCSV.class).with(schema);

        List<ProviderCarSpecificationsInCSV> providerCarSpecificationsInCSVList = new ArrayList<>();
        try (Reader reader = new FileReader(convertMultiPartToFile(csv))) {
            MappingIterator<ProviderCarSpecificationsInCSV> providerCarSpecificationsMappingIterator = objectReader.readValues(reader);
            while (providerCarSpecificationsMappingIterator.hasNext()) {
                providerCarSpecificationsInCSVList.add(providerCarSpecificationsMappingIterator.next());
            }
        }

      return providerCarSpecificationsInCSVList.stream().map(providerCarSpecificationsInCSV -> {
            String[] makeAndModel = providerCarSpecificationsInCSV.getMakeAndModel().split("/");
            return new ProviderListing(
                    providerId,
                    new CarListing(
                            makeAndModel[0],
                            makeAndModel[1],
                            providerCarSpecificationsInCSV.getPower(),
                            providerCarSpecificationsInCSV.getColor(),
                            providerCarSpecificationsInCSV.getPrice(),
                            providerCarSpecificationsInCSV.getYear()
                    ),
                    providerCarSpecificationsInCSV.getCode());
        }).collect(Collectors.toList());
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    private File convertMultiPartToFile(MultipartFile file ) throws IOException
    {
        File convFile = new File( "temp");
        FileOutputStream fos = new FileOutputStream( convFile);
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }


    /**
     *
     * @param providerCarSpecificationInJsonList
     * @param providerId
     * @return
     */
    public List<ProviderListing> map(List<ProviderCarSpecificationInJson> providerCarSpecificationInJsonList, Long providerId) {
      return providerCarSpecificationInJsonList.stream().map(providerCarSpecificationInJson -> {
          return new ProviderListing(
                    providerId,
                    new CarListing(
                            providerCarSpecificationInJson.getMake(),
                            providerCarSpecificationInJson.getModel(),
                            providerCarSpecificationInJson.getKW(),
                            providerCarSpecificationInJson.getColor(),
                            providerCarSpecificationInJson.getPrice(),
                            providerCarSpecificationInJson.getYear()
                    ),
                    providerCarSpecificationInJson.getCode());
        }).collect(Collectors.toList());
    }
}
