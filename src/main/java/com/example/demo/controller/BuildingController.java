package com.example.demo.controller;

import com.example.demo.builder.BuildingSearchBuilder;
import com.example.demo.dto.BuildingDTO;
import com.example.demo.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping()
    public Page<BuildingDTO> showBuilding(@RequestParam Map<String, String> model,
                                          @RequestParam String[] buildingTypes,
                                          @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                          @RequestParam(value = "size", defaultValue = "2", required = false) int size){
        BuildingSearchBuilder buildingSearchBuilder = BuildingSearchBuilder.builder()
                .name(model.get("name"))
                .district(model.get("district")).buildingArea(model.get("buildingArea"))
                .buildingTypes(buildingTypes)
                .street(model.get("street")).ward(model.get("ward")).numberOfBasement(model.get("numberOfBasement"))
                .costRentFrom(model.get("costRentFrom")).costRentTo(model.get("costRentTo"))
                .areaFrom(model.get("areaFrom")).areaTo(model.get("areaTo")).staffId(model.get("staffId"))
                .build();
        Pageable pageable = PageRequest.of(page - 1 , size);
        Page<BuildingDTO> response = this.buildingService.findAll(buildingSearchBuilder, pageable);

        return new PageImpl<>(response.getContent(), pageable, response.getTotalElements());

    }

}
