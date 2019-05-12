package com.ibm.cnnbfdc.utils;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import com.ibm.cnnbfdc.entity.RegionEntity;
import com.ibm.cnnbfdc.entity.SalesHouseEntity;
import com.ibm.cnnbfdc.service.RegionService;
import com.ibm.cnnbfdc.service.SaleHouseService;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class HttpUtils {


    @Autowired
    private RegionService regionService;

    @Autowired
    private SaleHouseService saleHouseService;

    private static final String CHARSET_UTF_8 = "UTF-8";

    public static String get(String url) {
        System.out.println(url.trim());
        HttpGet httpGet = new HttpGet(url.trim());
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        try {
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpGet);
            return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String post(String url, String param) {
        try {
            HttpPost httpPost = new HttpPost(url.trim());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            StringEntity se = new StringEntity(param.toString(), CHARSET_UTF_8);
            httpPost.setEntity(se);
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String postForm(String url, Map <String, String> params) {
        try {
            HttpPost httpPost = new HttpPost(url.trim());
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            BasicClientCookie cookie = new BasicClientCookie("PHPSESSID", "2brls80t268lhm9vhmadvkbko4");
            httpPost.setHeader("Cookies", cookie.toString());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            //StringEntity se = new StringEntity(param.toString(), CHARSET_UTF_8);
            List <NameValuePair> form = new ArrayList <>();
            for (String name : params.keySet()) {
                form.add(new BasicNameValuePair(name, params.get(name)));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form,
                    CHARSET_UTF_8);
            httpPost.setEntity(entity);
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<SalesHouseEntity> parseSaleHouse() throws NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException {
        String result = HttpUtils.get("https://newhouse.cnnbfdc.com/");
        String xpath = "//div[@class='cnnbfdc-ranking-list']/div[@style='max-width: 560px; float: left; width: 560px;']/div[@class='public-data']/div/div[@class='rc-table-content']/div[@class='rc-table-scroll']/div[@class='rc-table-body']/table/tbody[@class='rc-table-tbody']/tr";
        JXDocument jxDocument = new JXDocument(result);
        List <Object> rs = jxDocument.sel(xpath);
        List<SalesHouseEntity> salesHouseEntities = new ArrayList <>();
        for (Object o : rs) {
            Element tr = (Element) o;
            List <Element> tdList = tr.children();
            int count = 0;
            SalesHouseEntity salesHouseEntity = new SalesHouseEntity();
            for (Element e : tdList) {

                switch (count){
                    case 0: salesHouseEntity.setProjectName(e.text());break;
                    case 1: salesHouseEntity.setRegionName(e.text());break;
                    case 2: salesHouseEntity.setSaleCount(Integer.parseInt(e.text().substring(0,e.text().length()-1)));break;
                    case 3: salesHouseEntity.setSaleArea(Double.parseDouble(e.text().substring(0,e.text().length()-3)));break;

                }

                count++;

            }
            saleHouseService.updateIfExist(salesHouseEntity);
            salesHouseEntities.add(salesHouseEntity);

        }
        return salesHouseEntities;
    }

    public List<RegionEntity> parseRegion() throws NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException {
        String result = HttpUtils.get("https://newhouse.cnnbfdc.com/");
        String xpath = "//div[@class='cnnbfdc-ranking-list']/div[@style='max-width: 590px; float: right; width: 590px;']/div[@class='public-data']/div/div[@class='rc-table-content']/div[@class='rc-table-scroll']/div[@class='rc-table-body']/table/tbody[@class='rc-table-tbody']/tr";
        JXDocument jxDocument = new JXDocument(result);
        List <Object> rs = jxDocument.sel(xpath);

        List<RegionEntity> regionEntities = new ArrayList <>();
        for (Object o : rs) {
            Element tr = (Element) o;
            List <Element> tdList = tr.children();
            int count = 0;
            RegionEntity regionEntity = new RegionEntity();
            for (Element e : tdList) {

                switch (count){
                    case 0: regionEntity.setRegionName(e.text());break;
                    case 1: regionEntity.setSaleCount(Integer.parseInt(e.text().substring(0,e.text().length()-1)));break;
                    case 2: regionEntity.setSaleArea(Double.parseDouble(e.text().substring(0,e.text().length()-3)));break;
                    case 3: regionEntity.setAvailableSaleCount(Integer.parseInt(e.text().substring(0,e.text().length()-1)));break;
                    case 4: regionEntity.setAvailableSaleArea(Double.parseDouble(e.text().substring(0,e.text().length()-3)));break;

                }

                count++;

            }

            regionService.updateIfExist(regionEntity);
            regionEntities.add(regionEntity);

        }

        return regionEntities;
    }


    public static void main(String[] args) throws NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException {
//
//            HttpUtils.parseSaleHouse();
//        HttpUtils.parseRegion();
    }
}
