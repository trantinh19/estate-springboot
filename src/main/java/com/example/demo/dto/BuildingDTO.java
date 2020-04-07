package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonProperty("number_of_basement")
	private Integer numberOfBasement;
	@JsonProperty("building_area")
	private Integer buildingArea;
	private String structure;
	private Integer costRent;
	@JsonProperty("cost_description")
	private String costDescription;
	@JsonProperty("service_cost")
	private String serviceCost;
	private String carCost;
	@JsonProperty("motorbike_cost")
	private String motorbikeCost;
	@JsonProperty("overtime_cost")
	private String overtimeCost;
	@JsonProperty("electricity_cost")
	private String electricityCost;
	private String deposit;
	private String payment;
	@JsonProperty("time_rent")
	private String timeRent;
	@JsonProperty("time_decorator")
	private String timeDecorator;
	@JsonProperty("manager_name")
	private String managerName;
	@JsonProperty("manager_phone")
	private String managerPhone;
	private String type;

}
