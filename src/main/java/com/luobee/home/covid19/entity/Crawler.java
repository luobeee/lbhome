package com.luobee.home.covid19.entity;

public class Crawler {
    //丁香医生疫情页面
    public static final String URL ="https://3g.dxy.cn/newh5/view/pneumonia";
    //正则匹配（获取国内信息）
    public static final String AREA_INFORMATION_ATTRIBUTE="getAreaStat";
    public static final String AREA_INFORMATION_REGEX_TEMPLATE = "\\[(.*)\\]";
    //正则匹配（获取国外信息）
    public static final String COUNTRY_INFORMATION_ATTRIBUTE = "getListByCountryTypeService2true";
    public static final String COUNTRY_INFORMATION_REGEX_TEMPLATE = "\\[(.*)\\]";
    //正则匹配（获取统计信息）
//    public static final String STATIC_INFORMATION_ATTRIBUTE="getStatisticsService";
//    public static final String STATIC_INFORMATION_REGEX_TEMPLATE_1="\\{\"id\"(.*?\\}){38}";
//    public static final String STATIC_INFORMATION_REGEX_TEMPLATE_2="\\{(\"id\".*?)\\}\\]\\}";
//    public static final String STATIC_INFORMATION_REGEX_TEMPLATE_3="\\{(\"id\".*?)\\}\\}";
}
