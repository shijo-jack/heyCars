package com.heycar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class CarListing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
   private String make;

    @Column
    private String model;

    @Column
    private Integer kW;

    @Column
    private String color;

    @Column
    private BigDecimal price;

    @Column
    private Integer year;

    public CarListing(String make, String model, Integer kW, String color, BigDecimal price,
                      Integer year) {
        this.make = make;
        this.model = model;
        this.kW = kW;
        this.color = color;
        this.price = price;
        this.year = year;
    }
}
