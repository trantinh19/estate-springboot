package com.example.demo.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuildingSearchBuilder {

    private String name;
    private String district;
    private String buildingArea;
    private String street;
    private String ward;
    private String numberOfBasement;
    private String[] buildingTypes = new String[] {};
    private String costRentFrom;
    private String costRentTo;
    private String areaFrom;
    private String areaTo;
    private String staffId;

}
