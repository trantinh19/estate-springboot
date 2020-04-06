package com.example.demo.repository.building;

import com.example.demo.builder.BuildingSearchBuilder;
import com.example.demo.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuildingRepositoryCustom {
    Page<Building> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);
}
