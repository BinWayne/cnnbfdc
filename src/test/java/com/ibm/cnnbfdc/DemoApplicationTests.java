package com.ibm.cnnbfdc;

import com.ibm.cnnbfdc.dao.RegionDao;
import com.ibm.cnnbfdc.dao.SaleHouseDao;
import com.ibm.cnnbfdc.entity.RegionEntity;
import com.ibm.cnnbfdc.entity.SalesHouseEntity;
import com.ibm.cnnbfdc.service.RegionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private RegionService regionService;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private SaleHouseDao saleHouseDao;

	@Test
	public void testAddRegion(){
		RegionEntity entity = RegionEntity.builder().regionName("张三区")
				.availableSaleArea(123.44).availableSaleCount(23)
				.saleCount(22).saleArea(234.55)
				.build();
		SalesHouseEntity houseEntity = SalesHouseEntity.builder()
				.projectName("院子").saleArea(2342.323).saleCount(23)
				.regionName("张三区")
				.build();
		regionService.save(entity);
		//saleHouseDao.save(houseEntity);



	}

	@Test
	public void testUpdate(){
		RegionEntity entity = RegionEntity.builder().regionName("张三区")
				.availableSaleArea(123.44).availableSaleCount(23)
				.saleCount(22).saleArea(234.551111)
				.build();

		SalesHouseEntity houseEntity = SalesHouseEntity.builder()
				.projectName("院子").saleArea(2342.323111111).saleCount(23)
				.regionName("张三区")
				.build();
		saleHouseDao.updateSaleHouseEntity(houseEntity);
//		regionService.update(entity);
	}




}
