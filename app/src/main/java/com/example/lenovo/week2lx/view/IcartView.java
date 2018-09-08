package com.example.lenovo.week2lx.view;

import com.example.lenovo.week2lx.bean.CartBean;

/**
 * Created by lenovo on 2018/9/8.
 */

public  interface IcartView {
    void success(CartBean cartBean);
    void  failure(String msg);
}
