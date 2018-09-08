package com.example.lenovo.week2lx.common;

public class Api {
    public final static String BASE_URL = "http://www.zhaoapi.cn/";
    public final static String LOGIN_URL = BASE_URL+"user/login";
    public final static String REG_URL = BASE_URL+"user/reg";
    public final static String PRODUCT_URL = BASE_URL+"product/searchProducts";
    public final static String HOME_URL = BASE_URL+"home/getHome";
    public final static String GETCARTS =  BASE_URL+"product/getCarts";//获取购物车


    public static final String CLASS=BASE_URL+"product/getCatagory";//分类
    public static final String CLASS_CHILD=BASE_URL+"product/getProductCatagory";//子分类

//维度影院注册接口
public final static String WD_LOGIN = "http://172.17.8.100/movieApi/user/v1/registerUser";
}
