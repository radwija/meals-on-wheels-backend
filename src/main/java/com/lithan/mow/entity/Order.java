package com.lithan.mow.entity;

import java.util.Date;

import javax.persistence.*;

import com.lithan.mow.constraint.EStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordered_by")
    private Customer orderedBy;

    @ManyToOne
    @JoinColumn(name = "prepared_by")
    private Partner preparedBy;

    @ManyToOne
    @JoinColumn(name = "delivered_by")
    private Customer deliveredBy;

    @OneToOne
    private MealPackage mealPackage;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name = "ordered_on")
    private Date orderedOn;
}