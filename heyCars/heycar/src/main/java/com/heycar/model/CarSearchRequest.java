package com.heycar.model;

import lombok.Data;

@Data
public class CarSearchRequest {

    private String make;

    private String model;

    private Integer year;

    private String color;


}
