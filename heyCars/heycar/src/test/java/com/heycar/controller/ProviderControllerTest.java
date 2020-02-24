package com.heycar.controller;

import com.heycar.model.CarListing;
import com.heycar.repository.CarListingRepository;
import com.heycar.repository.ProviderListingRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProviderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CarListingRepository repository;

  @Autowired
  private ProviderListingRepository providerRepository;

  @After
  public void after(){
       providerRepository.deleteAll();
       repository.deleteAll();
  }

  @Test
  public void testCreateWithJSON() throws Exception {
      mockMvc.perform(post("/vehicle_listings/123/").content(
        "[\n"
            + "{\n"
            + "\"code\": \"a\",\n"
            + "\"make\": \"renault\",\n"
            + "\"model\": \"megane\",\n"
            + "\"kW\": 132,\n"
            + "\"year\": 2014,\n"
            + "\"color\": \"red\",\n"
            + "\"price\": 13990\n"
            + "}\n"
            + "]\n"
    )
        .contentType("application/json"))
        .andExpect(status().isCreated());

    List<CarListing> result = (List<CarListing>) repository.findAll();

    Assert.assertEquals(1, result.size());
    Assert.assertEquals("renault", result.get(0).getMake());
  }

  @Test
  public void testCreateWithCSV() throws Exception {
    String fileContents = "code,make/model,power-in-ps,year,color,price\n"
        + "1,Skoda/Rapid,123,2014,black,15950\n"
        + "2,Ford/Taurus,111,2016,white,17210\n"
        + "3,Toyota/Yairs,86,2018,green,14980\n"
        + "4,Renault/Kwid,86,2018,16990";

    MockMultipartFile csvFile = new MockMultipartFile(
        "test.json", "", "multipart/form-data", fileContents.getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload_csv/1/")
        .file("file", csvFile.getBytes())
        .characterEncoding("UTF-8"))
        .andExpect(status().isCreated());

    List<CarListing> result = (List<CarListing>) repository.findAll();

    Assert.assertEquals(4, result.size());

    Assert.assertEquals("Skoda", result.get(0).getMake());
    Assert.assertEquals("Rapid", result.get(0).getModel());
    Assert.assertEquals(2014, result.get(0).getYear().intValue());
    Assert.assertEquals("15950.00", result.get(0).getPrice().toString());
    Assert.assertEquals("black", result.get(0).getColor());

    Assert.assertEquals("Ford", result.get(1).getMake());
    Assert.assertEquals("Toyota", result.get(2).getMake());
    Assert.assertEquals("Renault", result.get(3).getMake());


  }

  @Test
  public void testUpdateWithCSV() throws Exception {

    String fileContents = "code,make/model,power-in-ps,year,color,price\n"
        + "1,Hummer/h2 180,123,2014,black,15950\n"
        + "2,Ford/Taurus,111,2016,white,17210\n"
        + "3,Toyota/Yairs,86,2018,green,14980\n"
        + "4,Renault/Kwid,86,2018,16990";

    MockMultipartFile csvFile = new MockMultipartFile(
        "test.json", "", "multipart/form-data", fileContents.getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload_csv/1/")
        .file("file", csvFile.getBytes())
        .characterEncoding("UTF-8"))
        .andExpect(status().isCreated());

     fileContents = "code,make/model,power-in-ps,year,color,price\n"
        + "1,Hummer/h2 180,123,1984,orange,15950\n"
        + "2,Ford/Taurus,111,2016,white,17210\n"
        + "3,Toyota/Yairs,86,2015,green,14980\n"
        + "4,Renault/Kwid,86,2018,16990";

     csvFile = new MockMultipartFile(
        "test.json", "", "multipart/form-data", fileContents.getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload_csv/1/")
        .file("file", csvFile.getBytes())
        .characterEncoding("UTF-8"))
        .andExpect(status().isCreated());

    List<CarListing> result = (List<CarListing>) repository.findAll();

    Assert.assertEquals(4, result.size());

    Assert.assertEquals("Hummer", result.get(0).getMake());
    Assert.assertEquals("h2 180", result.get(0).getModel());
    Assert.assertEquals(1984, result.get(0).getYear().intValue());
    Assert.assertEquals("15950.00", result.get(0).getPrice().toString());
    Assert.assertEquals("orange", result.get(0).getColor());
  }

}
