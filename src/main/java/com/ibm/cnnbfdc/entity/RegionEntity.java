package com.ibm.cnnbfdc.entity;

import lombok.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "region")
@ToString
@Cacheable
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int availableSaleCount; //可售套数
    private double availableSaleArea;// 可售面积
    private int saleCount;
    private double saleArea;
    private String regionName;

}
