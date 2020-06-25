package com.luobee.home.covid19.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.luobee.home.covid19.entity.Crawler;
import com.luobee.home.covid19.entity.Province;
import com.luobee.home.platform.util.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CrawlerService {
    public static Document page;

    public void getDxyJsoupPage(String URL) throws IOException {
        page = Jsoup.connect(URL).get();
    }

    /**
     * 正则匹配获取数据
     * @param regex
     * @param attributeKey
     * @param attributeValue
     * @return
     */
    public String getPageStrInfo(String regex, String attributeKey, String attributeValue){
        String info = null;
        //表达式对象
        Pattern pattern = Pattern.compile(regex);
        Elements timelineService = page.getElementsByAttributeValue(attributeKey,attributeValue);
        //创建Matcher对象
        Matcher matcher = pattern.matcher(timelineService.toString());
        //该方法扫描输入的序列，查找与该模式匹配的一个子序列
        if(matcher.find()) {
            info = matcher.group();
        }
        return info;
    }

    /**
     * 解析省份或者国家数据
     * @param provinceInformation
     * @return
     */
    public static List<Province> parseAreaInformation(String provinceInformation){
        List<Province> list = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(provinceInformation);
        for(Object jsonObj : jsonArray){
            Province province = JSON.toJavaObject((JSON) jsonObj, Province.class);
            list.add(province);
        }
        return list;
    }
}
