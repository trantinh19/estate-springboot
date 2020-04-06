package com.example.demo.repository.building.impl;

import com.example.demo.builder.BuildingSearchBuilder;
import com.example.demo.entity.Building;
import com.example.demo.repository.building.BuildingRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

//    public static void main(String[] args) {
//        List<String> wheres = new LinkedList<>();
//        wheres.add("b.name = :name");
//        wheres.add("b.district = :district");
//        String[] arr = new String[]{"TANG_TRET", "NGUYEN_CAN"};
//        String s = Arrays.stream(arr)
//                .map(e->"b.type LIKE '%"+e+"%'").collect(Collectors.joining(" OR "));
//        wheres.add(s);
//        String strWhere = String.join(" and " , wheres);
//        System.out.println(wheres);
//
//        System.out.println(s);
//        System.out.println(strWhere);
//    }


    @Override
    public Page<Building> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        List<String> wheres = new LinkedList<>();
        if(StringUtils.isNotBlank(buildingSearchBuilder.getName())) wheres.add("b.name like :name");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getWard())) wheres.add("b.ward like :ward");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getDistrict())) wheres.add("b.district like :district");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStreet())) wheres.add("b.street like :street");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getBuildingArea())) wheres.add("b.building_area = :buildingArea");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getNumberOfBasement())) wheres.add("b.number_of_basement = :numberOfBasement");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getCostRentFrom())) wheres.add("b.cost_rent >= :cosRentFrom");
        if(StringUtils.isNotBlank(buildingSearchBuilder.getCostRentTo())) wheres.add("b.cost_rent <= :cosRentTo");
        if (StringUtils.isNotBlank(buildingSearchBuilder.getAreaFrom()) || StringUtils.isNotBlank(buildingSearchBuilder.getAreaFrom())) {
            wheres.add("EXISTS (SELECT * FROM rent_area as ra WHERE ( ra.building_id = b.id and ra.value >= :areaFrom and ra.value <= :areaTo))");
        }
        if(buildingSearchBuilder.getBuildingTypes().length > 0) {
            String s = Arrays.stream(buildingSearchBuilder.getBuildingTypes())
                    .map(e->"b.type LIKE '%"+e+"%'").collect(Collectors.joining(" OR "));
            wheres.add(s);
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStaffId())) wheres.add("a.staff_id = :staffId");


        String strWhere = String.join(" and " , wheres);

        String sql = "select * from buildings as b";
        String count = "select count(*) from buildings as b";
        String join = "";
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStaffId())) {
            join += " inner join assignment_staffs as a on a.building_id = b.id";
        }
        if(StringUtils.isNotBlank(strWhere)) {
            sql += join + " where " + strWhere;
            count += join +  " where " + strWhere;
        }

        Query query = this.em.createNativeQuery(sql, Building.class);
        Query countQuery = this.em.createNativeQuery(count);
        if(StringUtils.isNotBlank(buildingSearchBuilder.getName())) {
            query.setParameter("name", "%" + buildingSearchBuilder.getName() + "%");
            countQuery.setParameter("name", "%" +buildingSearchBuilder.getName()+ "%");
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getWard())) {
            query.setParameter("ward", "%" + buildingSearchBuilder.getWard() + "%");
            countQuery.setParameter("ward","%" +  buildingSearchBuilder.getWard() + "%");
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStreet())) {
            query.setParameter("street", "%" + buildingSearchBuilder.getStreet() + "%");
            countQuery.setParameter("street", "%" + buildingSearchBuilder.getStreet() + "%");
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getDistrict())) {
            query.setParameter("district", "%" + buildingSearchBuilder.getDistrict() + "%");
            countQuery.setParameter("district","%" + buildingSearchBuilder.getDistrict() + "%");
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
        if(StringUtils.isNotBlank(buildingSearchBuilder.getAreaFrom()) || StringUtils.isNotBlank(buildingSearchBuilder.getAreaTo())) {
            query.setParameter("areaFrom", buildingSearchBuilder.getAreaFrom());
            countQuery.setParameter("areaFrom", buildingSearchBuilder.getAreaFrom());
            query.setParameter("areaTo", buildingSearchBuilder.getAreaTo());
            countQuery.setParameter("areaTo", buildingSearchBuilder.getAreaTo());
        }
        if(StringUtils.isNotBlank(buildingSearchBuilder.getStaffId())) {
            query.setParameter("staffId", buildingSearchBuilder.getStaffId());
            countQuery.setParameter("staffId", buildingSearchBuilder.getStaffId());
        }
//        if(buildingSearchBuilder.getBuildingTypes().length > 0 && StringUtils.isNotBlank(fieldSearch.getBuildingTypes()[0])) {
//            query.setParameter("buildingTypes", buildingSearchBuilder.getBuildingTypes());
//            countQuery.setParameter("buildingTypes", buildingSearchBuilder.getBuildingTypes());
//        }
        BigInteger total = (BigInteger) countQuery.getSingleResult();
        if(total == null) return new PageImpl<>(Collections.emptyList(), pageable, 0);

        List<Building> result = query.getResultList();
        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(result, pageable, total.longValue());
    }

}
