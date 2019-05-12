package com.ibm.cnnbfdc.dao;

import com.ibm.cnnbfdc.entity.RegionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegionDao extends JpaRepository<RegionEntity, Integer>,RegionCustomizedLogRepository {


}
