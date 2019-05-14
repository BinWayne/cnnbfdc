package com.ibm.cnnbfdc.service;

import com.ibm.cnnbfdc.dao.SaleHouseDao;
import com.ibm.cnnbfdc.entity.SalesHouseEntity;
import org.hibernate.query.criteria.internal.SelectionImplementor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SaleHouseService {
    @Resource
    private SaleHouseDao saleHouseDao;

    @Transactional
    public void updateIfExist(SalesHouseEntity salesHouseEntity){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = simpleDateFormat.format(new Date()).concat(" 00:00:00");
        String endDate = simpleDateFormat.format(new Date()).concat(" 23:59:59");
        System.out.println(startDate +": "+endDate);
        SalesHouseEntity result = saleHouseDao.findByName(salesHouseEntity.getProjectName(),startDate,endDate);

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

    public List<SalesHouseEntity> findByDate(String s,String e){
        System.out.println("start date ->" +s);
        System.out.println("e date ->" +e);
        return saleHouseDao.findByDate(s,e);
    }
}
