package com.example.demo.repository.impl;

import com.example.demo.builder.BuildingSearchBuilder;
import com.example.demo.entity.Building;
import com.example.demo.repository.BuildingRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Page<Building> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        List<String> wheres = new LinkedList<>();
        if(StringUtils.isNotBlank(buildingSearchBuilder.getName())) wheres.add("b.name = :name");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getWard())) wheres.add("b.ward = :ward");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getDistrict())) wheres.add("b.district = :district");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStreet())) wheres.add("b.street = :street");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getBuildingArea())) wheres.add("b.buildingArea = :buildingArea");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getNumberOfBasement())) wheres.add("b.numberOfBasement = :numberOfBasement");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getCostRentFrom())) wheres.add("b.cosRentFrom >= :cosRentFrom");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getCostRentTo())) wheres.add("b.cosRentTo <= :cosRentTo");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getAreaFrom())) wheres.add("b.areaFrom >= :areaFrom");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getAreaTo())) wheres.add("b.areaTo <= :areaTo");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStaffId())) wheres.add("a.staffId = :staffId");

        String strWhere = String.join(" and " , wheres);

        String sql = "select b from Building b";
        String count = "select count(b) from Building b";
        String join = "";
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStaffId())) {
            join += " inner join AssignmentStaff a on a.id = b.id";
        }
        if(StringUtils.isNotBlank(strWhere)) {
            sql += join + " where " + strWhere;
            count += join +  " where " + strWhere;
        }

        TypedQuery<Building> query = this.em.createQuery(sql, Building.class);
        TypedQuery<Long> countQuery = this.em.createQuery(count, Long.class);
        if(StringUtils.isNotBlank(buildingSearchBuilder.getName())) {
            query.setParameter("name", buildingSearchBuilder.getName());
            countQuery.setParameter("name", buildingSearchBuilder.getName());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getWard())) {
            query.setParameter("ward", buildingSearchBuilder.getWard());
            countQuery.setParameter("ward", buildingSearchBuilder.getWard());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStreet())) {
            query.setParameter("street", buildingSearchBuilder.getStreet());
            countQuery.setParameter("street", buildingSearchBuilder.getStreet());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getDistrict())) {
            query.setParameter("district", buildingSearchBuilder.getDistrict());
            countQuery.setParameter("district", buildingSearchBuilder.getDistrict());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getBuildingArea())) {
            query.setParameter("buildingArea", buildingSearchBuilder.getBuildingArea());
            countQuery.setParameter("buildingArea", buildingSearchBuilder.getBuildingArea());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getNumberOfBasement())) {
            query.setParameter("numberOfBasement", buildingSearchBuilder.getNumberOfBasement());
            countQuery.setParameter("numberOfBasement", buildingSearchBuilder.getNumberOfBasement());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getCostRentFrom())) {
            query.setParameter("cosRentFrom", buildingSearchBuilder.getCostRentFrom());
            countQuery.setParameter("cosRentFrom", buildingSearchBuilder.getCostRentFrom());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getCostRentTo())) {
            query.setParameter("cosRentTo", buildingSearchBuilder.getCostRentTo());
            countQuery.setParameter("cosRentTo", buildingSearchBuilder.getCostRentTo());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getAreaFrom())) {
            query.setParameter("areaFrom", buildingSearchBuilder.getAreaFrom());
            countQuery.setParameter("areaFrom", buildingSearchBuilder.getAreaFrom());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getAreaTo())) {
            query.setParameter("areaTo", buildingSearchBuilder.getAreaTo());
            countQuery.setParameter("areaTo", buildingSearchBuilder.getAreaTo());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStaffId())) {
            query.setParameter("staffId", buildingSearchBuilder.getStaffId());
            countQuery.setParameter("staffId", buildingSearchBuilder.getStaffId());
        }

        Long total = countQuery.getSingleResult();
        if(total == null || total < 0) return new PageImpl<>(Collections.emptyList(), pageable, 0);

        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Building> result = query.getResultList();

        return new PageImpl<>(result, pageable, total);
    }

}
