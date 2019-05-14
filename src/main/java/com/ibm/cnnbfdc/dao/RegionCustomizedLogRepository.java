package com.ibm.cnnbfdc.dao;

import com.ibm.cnnbfdc.entity.RegionEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperation;

import javax.swing.plaf.synth.Region;
import java.util.List;

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
            value="select * from region where region_name = :regionName  and created_at between :startDate and :endDate",
            nativeQuery = true
    )
    public RegionEntity findByRegionName(@Param("regionName")String regionName,@Param("startDate")String startDate,@Param("endDate")String endDate);



//    @Modifying
//    @Query(
//            value = "insert into region (" +
//                    "available_sale_area," +
//                    "available_sale_count," +
//                    "region_name," +
//                    "sale_area," +
//                    "sale_count) VALUES (" +
//                    ":#{#region.availableSaleArea}," +
//                    ":#{#region.availableSaleCount}," +
//                    ":#{#region.regionName}," +
//                    ":#{#region.saleArea}," +
//                    ":#{#region.saleCount})",
//            nativeQuery = true
//    )
//    public void insertRegion(@Param("region")RegionEntity entity);

    @Query(
            value="select * from region where  created_at between :startDate and :endDate",
            nativeQuery = true
    )
    public List<RegionEntity> findByDate(@Param("startDate")String startDate,@Param("endDate")String endDate);
}
