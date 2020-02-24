package com.heycar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.heycar.model.CarListing;
import com.heycar.repository.CarListingRepository;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CarListingRepository repository;


  @Before
  public void setup() {
    repository.save(new CarListing("make", "model", 1234, "color", new BigDecimal(1000), 2019));
    repository.save(new CarListing("Vauxhall", "Astra", 1234, "red", new BigDecimal(1000), 2019));
    repository.save(new CarListing("Vauxhall", "Corsa", 1234, "red", new BigDecimal(1000), 2000));

    repository.save(new CarListing("Mercedes", "A", 123, "black", new BigDecimal(15950), 2014));
    repository.save(new CarListing("Audi", "a3", 1111, "white", new BigDecimal(17210), 2016));
    repository.save(new CarListing("vw", "golf", 86, "green", new BigDecimal(14980), 2018));
    repository.save(new CarListing("skoda", "octavia", 86, "green", new BigDecimal(16990), 2018));
  }

  @After
  public void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void testSearchWithNoParam() throws Exception {

    mockMvc.perform(get("/search"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("content.[0].make").value("make"))
        .andExpect(jsonPath("content.[0].model").value("model"))
        .andExpect(jsonPath("content.[0].kw").value("1234"))
        .andExpect(jsonPath("content.[0].color").value("color"))
        .andExpect(jsonPath("content.[0].price").value("1000.0"))
        .andExpect(jsonPath("content.[0].year").value("2019"));
  }

  @Test
  public void testSearchByMake() throws Exception {
    mockMvc.perform(get("/search?make=Vauxhall"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("content.[0].make").value("Vauxhall"))
        .andExpect(jsonPath("content.[0].model").value("Astra"))
        .andExpect(jsonPath("content.[0].kw").value("1234"))
        .andExpect(jsonPath("content.[0].color").value("red"))
        .andExpect(jsonPath("content.[0].price").value("1000.0"))
        .andExpect(jsonPath("content.[0].year").value("2019"))

        .andExpect(jsonPath("content.[1].make").value("Vauxhall"))
        .andExpect(jsonPath("content.[1].model").value("Corsa"))
        .andExpect(jsonPath("content.[1].kw").value("1234"))
        .andExpect(jsonPath("content.[1].color").value("red"))
        .andExpect(jsonPath("content.[1].price").value("1000.0"))
        .andExpect(jsonPath("content.[1].year").value("2000"));
  }

  @Test
  public void testSearchByModel() throws Exception {
    mockMvc.perform(get("/search?model=Corsa"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("content.[0].make").value("Vauxhall"))
        .andExpect(jsonPath("content.[0].model").value("Corsa"))
        .andExpect(jsonPath("content.[0].kw").value("1234"))
        .andExpect(jsonPath("content.[0].color").value("red"))
        .andExpect(jsonPath("content.[0].price").value("1000.0"))
        .andExpect(jsonPath("content.[0].year").value("2000"));
  }

  @Test
  public void testSearchByYear() throws Exception {

    mockMvc.perform(get("/search?year=2014"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("content.[0].make").value("Mercedes"))
        .andExpect(jsonPath("content.[0].model").value("A"))
        .andExpect(jsonPath("content.[0].kw").value("123"))
        .andExpect(jsonPath("content.[0].color").value("black"))
        .andExpect(jsonPath("content.[0].price").value("15950.0"))
        .andExpect(jsonPath("content.[0].year").value("2014"));
  }

  @Test
  public void testSearchByColor() throws Exception {
    mockMvc.perform(get("/search?color=white"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("content.[0].make").value("Audi"))
        .andExpect(jsonPath("content.[0].model").value("a3"))
        .andExpect(jsonPath("content.[0].kw").value("1111"))
        .andExpect(jsonPath("content.[0].color").value("white"))
        .andExpect(jsonPath("content.[0].price").value("17210.0"))
        .andExpect(jsonPath("content.[0].year").value("2016"));
  }


}
