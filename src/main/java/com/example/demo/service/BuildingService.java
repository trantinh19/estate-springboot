package com.example.demo.service;

import com.example.demo.builder.BuildingSearchBuilder;
import com.example.demo.dto.BuildingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BuildingService {
    Page<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);
    Map<String, String> getDistricts();
    Map<String, String> getBuildingTypes();
}
