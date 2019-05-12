package com.ibm.cnnbfdc.utils;


import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://a.qidian.com/")
@ExtractBy(value = "//ul[@class=\"all-img-list cf\"]/li",multi = true)
public class GithubRepoPageProcessor {
    @ExtractBy("//div[@class=book-mid-info]/h4/a/text()")
    private String title;
    @ExtractBy("//div[@class=book-mid-info]/p[@class=author]/a[@class=name]/text()")
    private String author;

    @ExtractBy("//div[@class=book-mid-info]/p[@class=author]/a[@class=go-sub-type]/text()")
    private String type;
    @ExtractBy("//div[@class=book-mid-info]/p[@class=author]/span/text()")
    private String status;

    @ExtractBy("//div[@class=book-mid-info]/p[@class=intro]/text()")
    private String intro;
    @ExtractBy("//div[@class=book-mid-info]/p[@class=update]/span/text()")
    private String count;

    public static void main(String[] args) {
        OOSpider.create(Site.me(), new ConsolePageModelPipeline(), GithubRepoPageProcessor.class).addUrl("http://a.qidian.com/").thread(1).run();
//         OOSpider.create(Site.me().setSleepTime(100), new ConsolePageModelPipeline(), GithubRepoPageProcessor.class);



    }
}
