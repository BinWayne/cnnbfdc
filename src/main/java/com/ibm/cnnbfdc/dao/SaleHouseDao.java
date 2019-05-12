package com.ibm.cnnbfdc.dao;




import com.ibm.cnnbfdc.entity.SalesHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SaleHouseDao extends JpaRepository<SalesHouseEntity, Integer>,SaleHouseCustomizedLogRepository {

}
