package com.ibm.cnnbfdc.utils;


import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;


@TargetUrl("http://newhouse.cnnbfdc.com/")
@ExtractBy(value="//div[@id=cnnbfdc-ranking-list]")
@Getter
@Setter
public class CnnbfdcPageProcessor {

    @ExtractBy("//h2/text())")
    private String name;



    public static void main(String[] args) {
         OOSpider.create(Site.me().setSleepTime(1000), new ConsolePageModelPipeline(),CnnbfdcPageProcessor.class)
                 .addUrl("http://newhouse.cnnbfdc.com/").thread(1).run();




    }
}
