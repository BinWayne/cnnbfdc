package com.ibm.cnnbfdc.dao;


import com.ibm.cnnbfdc.entity.SalesHouseEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SaleHouseCustomizedLogRepository {


    /**
     *
     * /**
     *      * id |created_at          |project_name |region_name |sale_area |sale_count |
     *      * ---|--------------------|-------------|------------|----------|-----------|
     *      private String regionName;//项目区域
     *      *     private int saleCount;//销售套数
     *      *     private double saleArea;//销售面积
     *      *     @Column(name = "created_at")
     *      *     private Date createdAt;
     *      */
    @Modifying
    @Transactional
    @Query(value="update saleshouse s set s.region_name = :#{#saleHouse.regionName} ," +

            "s.sale_area = :#{#saleHouse.saleArea} ," +
            "s.sale_count = :#{#saleHouse.saleCount} " +

            "where s.project_name = :#{#saleHouse.projectName}",
            nativeQuery=true)
    public void updateSaleHouseEntity(@Param("saleHouse")SalesHouseEntity saleHouse);




    @Query(
            value="select * from saleshouse where project_name = :projectName and created_at between :startDate and :endDate",
            nativeQuery = true
    )
    public SalesHouseEntity findByName(@Param("projectName")String projectName,@Param("startDate")String startDate,@Param("endDate")String endDate);



    @Query(
            value="select * from saleshouse where created_at between :startDate and :endDate",
            nativeQuery = true
    )
    public List<SalesHouseEntity> findByDate(@Param("startDate")String startDate,@Param("endDate")String endDate);


}
