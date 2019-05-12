package com.ibm.cnnbfdc.dao;

import com.ibm.cnnbfdc.entity.RegionEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegionCustomizedLogRepository {



    @Modifying
    @Query(value="update region r set r.available_sale_area = :#{#region.availableSaleArea} ," +
            "r.available_sale_count =  :#{#region.availableSaleCount} ," +
            "r.sale_area = :#{#region.saleArea} ," +
            "r.sale_count = :#{#region.saleCount} " +
            "where r.region_name = :#{#region.regionName}",
            nativeQuery=true)
    public void updateRegionEntity(@Param("region")RegionEntity region);

    @Query(
            value="select * from region where region_name = :regionName",
            nativeQuery = true
    )
    public RegionEntity findByRegionName(@Param("regionName")String regionName);

    /**
     *
     * available_sale_area |available_sale_count |region_name |sale_area |sale_count
     *
     *  private int availableSaleCount; //可售套数
     *     private double availableSaleArea;// 可售面积
     *     private int saleCount;
     *     private double saleArea;
     *     private String regionName;
     * */
}
