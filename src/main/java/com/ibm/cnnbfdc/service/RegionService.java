package com.ibm.cnnbfdc.service;

import com.ibm.cnnbfdc.dao.RegionDao;
import com.ibm.cnnbfdc.entity.RegionEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegionService {

    @Resource
    private RegionDao regionDao;

    @Transactional
    public void save(RegionEntity entity){
        regionDao.save(entity);
    }

    @Transactional
    public void update(RegionEntity entity){
        regionDao.updateRegionEntity(entity);
    }

    @Transactional
    public RegionEntity findByName(String name){
        return regionDao.findByRegionName(name);
    }

    @Transactional
    public void updateIfExist(RegionEntity regionEntity){

        RegionEntity result  = regionDao.findByRegionName(regionEntity.getRegionName());
        if(result!=null){
            System.out.println("已经存在 区域->" + regionEntity.getRegionName()+",进行更新");
            regionDao.updateRegionEntity(regionEntity);
        }else{
           regionDao.save(regionEntity);
        }
    }

    public List<RegionEntity> findAll(){
        return regionDao.findAll();
    }

}
