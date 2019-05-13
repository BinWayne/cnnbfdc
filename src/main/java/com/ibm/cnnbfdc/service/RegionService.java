package com.ibm.cnnbfdc.service;

import com.ibm.cnnbfdc.dao.RegionDao;
import com.ibm.cnnbfdc.entity.RegionEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.crypto.dsig.SignatureMethod;
import java.text.SimpleDateFormat;
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
            if(result.equals(regionEntity)){
                System.out.println("region 完全一样!!");
                return;
            }
            if(!(result.getCreatedAt().equals(regionEntity.getCreatedAt()))){
                System.out.println("日期不同 region 查询出来日期->"+result.getCreatedAt()+"，将要插入日期-> "+regionEntity.getCreatedAt());
                regionDao.save(regionEntity);
            }else{
                System.out.println("region 某些数据不一样");
                System.out.println("result --> " +result.toString());
                System.out.println("regionEntity --> " +regionEntity.toString());


                regionDao.updateRegionEntity(regionEntity);
            }
        }else{
            regionDao.save(regionEntity);
        }


    }

    public List<RegionEntity> findAll(){
        return regionDao.findAll();
    }

}
