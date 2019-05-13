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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/fetch")
public class FetchInfoController {


  @Autowired
  private HttpUtils httpUtils;

  @Autowired
  private RegionService regionService;

  @Autowired
  private SaleHouseService saleHouseService;

    @RequestMapping("/region/allinfo")
    public String fetchRegionInfo(Model model) throws NoSuchFunctionException, NoSuchAxisException, XpathSyntaxErrorException {

        List<RegionEntity> regionEntities = regionService.findAll();
        model.addAttribute("regionEntities", regionEntities);
        return "regionListInfo";
    }

    @RequestMapping("/house/allinfo")
    public  String fetchHouseInfo(Model model) throws NoSuchFunctionException, NoSuchAxisException, XpathSyntaxErrorException {


        List<SalesHouseEntity> salesHouseEntities = saleHouseService.findAll();
        model.addAttribute("salesHouseEntities", salesHouseEntities);
        return "houseListInfo";
    }

    @RequestMapping("/loader/info")
    public String loader(Model model)  {
        try {
            List<SalesHouseEntity> salesHouseEntities = httpUtils.parseSaleHouse();
            List<RegionEntity> regionEntities = httpUtils.parseRegion();
        } catch (NoSuchFunctionException e) {

            model.addAttribute("errMsg",e.getMessage());
            return "fail";
        } catch (XpathSyntaxErrorException e) {

            model.addAttribute("errMsg",e.getMessage());
            return "fail";
        } catch (NoSuchAxisException e) {

            model.addAttribute("errMsg",e.getMessage());
            return "fail";
        }finally {

        }

        model.addAttribute("successMsg","load success");
        return "success";

    }


}
