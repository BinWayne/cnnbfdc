package com.ibm.cnnbfdc.service;

import com.ibm.cnnbfdc.dao.SaleHouseDao;
import com.ibm.cnnbfdc.entity.SalesHouseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SaleHouseService {
    @Resource
    private SaleHouseDao saleHouseDao;

    @Transactional
    public void updateIfExist(SalesHouseEntity salesHouseEntity){

        SalesHouseEntity result  = saleHouseDao.findByName(salesHouseEntity.getProjectName());
        if(result!=null){
            System.out.println("已经存在 项目->" + salesHouseEntity.getProjectName()+",进行更新");
            saleHouseDao.updateSaleHouseEntity(salesHouseEntity);
        }else{
            saleHouseDao.save(salesHouseEntity);
        }
    }

    public List<SalesHouseEntity> findAll(){

        return saleHouseDao.findAll();
    }
}
