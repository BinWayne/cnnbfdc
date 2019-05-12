package com.ibm.cnnbfdc.controller;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import com.ibm.cnnbfdc.entity.RegionEntity;
import com.ibm.cnnbfdc.entity.SalesHouseEntity;
import com.ibm.cnnbfdc.service.RegionService;
import com.ibm.cnnbfdc.service.SaleHouseService;
import com.ibm.cnnbfdc.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fetch")
public class FetchInfoController {


  @Autowired
  private HttpUtils httpUtils;

  @Autowired
  private RegionService regionService;

  @Autowired
  private SaleHouseService saleHouseService;

    @RequestMapping("/region/allinfo")
    public List<RegionEntity> fetchRegionInfo() throws NoSuchFunctionException, NoSuchAxisException, XpathSyntaxErrorException {

        return regionService.findAll();


    }

    @RequestMapping("/house/allinfo")
    public List<SalesHouseEntity> fetchHouseInfo() throws NoSuchFunctionException, NoSuchAxisException, XpathSyntaxErrorException {


       return saleHouseService.findAll();

    }

    @RequestMapping("/loader/info")
    public String loader()  {
        try {
            List<SalesHouseEntity> salesHouseEntities = httpUtils.parseSaleHouse();
            List<RegionEntity> regionEntities = httpUtils.parseRegion();
        } catch (NoSuchFunctionException e) {
            e.printStackTrace();
            return "fail";
        } catch (XpathSyntaxErrorException e) {
            e.printStackTrace();
            return "fail";
        } catch (NoSuchAxisException e) {
            e.printStackTrace();
            return "fail";
        }finally {

        }

        return "success";

    }


}
