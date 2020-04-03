package com.example.demo.service.impl;

import com.example.demo.builder.BuildingSearchBuilder;
import com.example.demo.dto.BuildingDTO;
import com.example.demo.entity.Building;
import com.example.demo.enums.BuildingTypes;
import com.example.demo.enums.Districts;
import com.example.demo.repository.BuildingRepository;
import com.example.demo.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Page<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        Page<Building> pageResult = this.buildingRepository.findAll(buildingSearchBuilder, pageable);
        List<BuildingDTO> buildingDTOs = pageResult.getContent()
                                            .stream()
                                            .map(el -> mapper.map(el, BuildingDTO.class))
                                            .collect(Collectors.toList());
        return new PageImpl<>(buildingDTOs, pageable, pageResult.getTotalElements());
    }

    @Override
    public Map<String, String> getDistricts() {
        Map<String , String> districts = new HashMap<>();
        for (Districts item : Districts.values()){
            districts.put(item.toString(), item.getValue());
        }
        return districts;
    }

    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String , String> buildings = new HashMap<>();
        for (BuildingTypes item : BuildingTypes.values()){
            buildings.put(item.toString(), item.getValue());
        }
        return buildings;
    }
}
