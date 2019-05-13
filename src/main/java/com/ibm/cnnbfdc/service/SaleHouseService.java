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
            if(result.equals(salesHouseEntity)){
                System.out.println("sale house 完全一样!!");
                return;
            }
            if(!(result.getCreatedAt().equals(salesHouseEntity.getCreatedAt()))){
                System.out.println("日期不同 sales house 查询出来日期->"+result.getCreatedAt()+"，将要插入日期-> "+salesHouseEntity.getCreatedAt());
                saleHouseDao.save(salesHouseEntity);
            }else{
                System.out.println("sale house 部分不同");
                System.out.println("sale house result --> "+result.toString());
                System.out.println("sale house entity --> "+salesHouseEntity.toString());
                saleHouseDao.updateSaleHouseEntity(salesHouseEntity);
            }


        }else{
            saleHouseDao.save(salesHouseEntity);
        }
    }

    public List<SalesHouseEntity> findAll(){

        return saleHouseDao.findAll();
    }
}
