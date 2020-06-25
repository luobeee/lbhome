package com.luobee.home.covid19.web;

import com.luobee.home.covid19.entity.Crawler;
import com.luobee.home.covid19.entity.Province;
import com.luobee.home.covid19.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CrawlerController {
    CrawlerService crawlerService;
    @Autowired
    public void setUserDao(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @PostMapping("/covid19_crawler_province")
    public List<Province> getProvinceStaInfo(){
        List<Province> provinceStaList = null;
        try {
            //初始化HTML数据
            crawlerService.getDxyJsoupPage(Crawler.URL);
            //获取json数据
            String provinceInformation = crawlerService.getPageStrInfo(Crawler.AREA_INFORMATION_REGEX_TEMPLATE, "id", Crawler.AREA_INFORMATION_ATTRIBUTE);
            provinceStaList = crawlerService.parseAreaInformation(provinceInformation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return provinceStaList;
    }

    @PostMapping("/covid19_crawler_country")
    public List<Province> getCountryStaInfo(){
        List<Province> countryStaList = null;
        try {
            //初始化HTML数据
            crawlerService.getDxyJsoupPage(Crawler.URL);
            //获取json数据
            String provinceInformation = crawlerService.getPageStrInfo(Crawler.COUNTRY_INFORMATION_REGEX_TEMPLATE, "id", Crawler.COUNTRY_INFORMATION_ATTRIBUTE);
            countryStaList = crawlerService.parseAreaInformation(provinceInformation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryStaList;
    }
}
