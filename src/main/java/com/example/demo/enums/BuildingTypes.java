package com.example.demo.enums;

public enum BuildingTypes {

    TANG_TRET("Tầng Trệt"),
    NGUYEN_CAN("Nguyên Căn"),
    NOI_THAT("Nội Thất");

    private final String value;

    BuildingTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
