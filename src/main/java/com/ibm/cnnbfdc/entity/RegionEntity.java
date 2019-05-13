package com.ibm.cnnbfdc.entity;

import lombok.*;

import javax.persistence.*;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.text.SimpleDateFormat;
import java.util.Date;


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

    @Column(name = "created_at")
    private Date createdAt;



    public int getAvailableSaleCount() {
        return availableSaleCount;
    }

    public double getAvailableSaleArea() {
        return availableSaleArea;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public double getSaleArea() {
        return saleArea;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCreatedAt() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


        if(createdAt == null){
            createdAt = new Date();
        }
        return simpleDateFormat.format(createdAt);
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  RegionEntity)){
            return false;
        }
        RegionEntity r = (RegionEntity)obj;


        if(this.getRegionName().equals(r.getRegionName())
                && this.getAvailableSaleArea()== r.getAvailableSaleArea()
                && this.getAvailableSaleCount() == r.getAvailableSaleCount()
                && this.getSaleArea() == r.getSaleArea()
                && this.getSaleCount() == r.getSaleCount()
                && this.getCreatedAt().equals(r.getCreatedAt())
                && this.getId() != r.getId()
                || this.getId() == r.getId()
                ){
            return true;
        }
       return false;
    }

    public static void main(String[] args) {

    }
}
