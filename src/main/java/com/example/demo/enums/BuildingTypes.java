package com.example.demo.enums;

public enum BuildingTypes {

    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String value;

    BuildingTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
