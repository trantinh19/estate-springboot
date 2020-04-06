package com.example.demo.repository.building;

import com.example.demo.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long>, BuildingRepositoryCustom {
}
