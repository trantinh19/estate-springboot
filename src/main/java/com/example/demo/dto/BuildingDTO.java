package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDTO extends AbstractBaseDTO{
	
	private String name;
	private String street;
	private String ward;
	private String district;
	private Integer numberOfBasement;
	private Integer buildingArea;
	private String structure;
	private Integer costRent;
	private String costDescription;
	private String serviceCost;
	private String carCost;
	private String motorbikeCost;
	private String overtimeCost;
	private String electricityCost;
	private String deposit;
	private String payment;
	private String timeRent;
	private String timeDecorator;
	private String managerName;
	private String managerPhone;
	private String type;

}
