package com.ibm.cnnbfdc.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "saleshouse")
@ToString
@Cacheable
public class SalesHouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String projectName;//项目名称
    private String regionName;//项目区域
    private int saleCount;//销售套数
    private double saleArea;//销售面积
    @Column(name = "created_at")
    private Date createdAt;


    @PrePersist
    void createdAt() {
        this.createdAt =  new Date();
    }

    public int getSaleCount() {
        return saleCount;
    }

    public double getSaleArea() {
        return saleArea;
    }

    public String getProjectName() {
        return projectName;
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
        if(!(obj instanceof SalesHouseEntity)){
            return false;

        }

        SalesHouseEntity salesHouseEntity = (SalesHouseEntity)obj;
        if(this.getProjectName().equals(salesHouseEntity.getProjectName())
                && this.getRegionName().equals(salesHouseEntity.getRegionName())
                && this.getSaleArea() == salesHouseEntity.getSaleArea()
                && this.getSaleCount() == salesHouseEntity.getSaleCount()
                && this.getCreatedAt().equals(salesHouseEntity.getCreatedAt())
                && this.getId() != salesHouseEntity.getId()
                || this.getId() == salesHouseEntity.getId()){
            return true;
        }
        return false;
    }
}
