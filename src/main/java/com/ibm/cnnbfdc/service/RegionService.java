package com.ibm.cnnbfdc.service;

import com.ibm.cnnbfdc.dao.RegionDao;
import com.ibm.cnnbfdc.entity.RegionEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void updateIfExist(RegionEntity regionEntity){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = simpleDateFormat.format(new Date()).concat(" 00:00:00");
        String endDate = simpleDateFormat.format(new Date()).concat(" 23:59:59");
        System.out.println(startDate +": "+endDate);
        RegionEntity result  = regionDao.findByRegionName(regionEntity.getRegionName(),startDate,endDate);


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


    public List<RegionEntity> findByDate(String startDate,String endDate){
        System.out.println("start date -> "+ startDate);
        System.out.println("end date -> "+ endDate);
        return regionDao.findByDate(startDate,endDate);
    }
}
