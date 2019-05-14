package com.ibm.cnnbfdc.controller;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import com.ibm.cnnbfdc.entity.RegionEntity;
import com.ibm.cnnbfdc.entity.SalesHouseEntity;
import com.ibm.cnnbfdc.service.RegionService;
import com.ibm.cnnbfdc.service.SaleHouseService;
import com.ibm.cnnbfdc.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String fetchRegionInfo(Model model,@RequestParam(value = "s",required = false) String startDate,@RequestParam(value = "e",required = false) String endDate) throws NoSuchFunctionException, NoSuchAxisException, XpathSyntaxErrorException {
        List<RegionEntity> regionEntities = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if(StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)){
            regionEntities = regionService.findAll();
        }else{
            if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){

                startDate = startDate.concat(" 00:00:00");
                endDate = endDate.concat(" 23:59:59");
                regionEntities = regionService.findByDate(startDate,endDate);
            }else if(StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endDate)){
                startDate = startDate.concat(" 00:00:00");
                endDate = simpleDateFormat.format(new Date());

                endDate = endDate.concat(" 23:59:59");
                regionEntities = regionService.findByDate(startDate,endDate);
            }

        }

        model.addAttribute("regionEntities", regionEntities);
        return "regionListInfo";
    }

    @RequestMapping("/house/allinfo")
    public  String fetchHouseInfo(Model model,@RequestParam(value = "s",required = false) String startDate,@RequestParam(value = "e",required = false) String endDate) throws NoSuchFunctionException, NoSuchAxisException, XpathSyntaxErrorException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<SalesHouseEntity> salesHouseEntities=null;
        if(StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)){
            salesHouseEntities = saleHouseService.findAll();
        }else {
            if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {

                startDate = startDate.concat(" 00:00:00");
                endDate = endDate.concat(" 23:59:59");
                salesHouseEntities = saleHouseService.findByDate(startDate,endDate);
            }else if(StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endDate)){
                startDate = startDate.concat(" 00:00:00");
                endDate = simpleDateFormat.format(new Date());
                endDate = endDate.concat(" 23:59:59");
                salesHouseEntities = saleHouseService.findByDate(startDate,endDate);
            }

        }

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
