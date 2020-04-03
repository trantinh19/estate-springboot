package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buildings")
public class Building extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_basement")
    private Integer numberOfBasement;

    @Column(name = "building_area")
    private Integer buildingArea;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "structure")
    private String structure;

    @Column(name = "cost_rent")
    private Integer costRent;

    @Column(name = "cost_description", columnDefinition = "TEXT")
    private String costDescription;

    @Column(name = "service_cost")
    private String serviceCost;

    @Column(name = "car_cost")
    private String carCost;

    @Column(name = "motor_bike_Cost")
    private String motorBikeCost;

    @Column(name = "over_time_cost")
    private String overTimeCost;

    @Column(name = "electricity_cost")
    private String electricityCost;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "payment")
    private String payment;

    @Column(name = "time_rent")
    private String timeRent;

    @Column(name = "time_decorator")
    private String timeDecorator;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_phone")
    private String managerPhone;

    @Column(name = "type", columnDefinition = "TEXT")
    private String type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignment_staffs", joinColumns = @JoinColumn(name = "building_id"),
                            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private List<User> staffs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "building")
    private List<RentArea> rentAreas = new ArrayList<>();

}
