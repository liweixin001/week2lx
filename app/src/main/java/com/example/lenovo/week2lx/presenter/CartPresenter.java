package com.example.lenovo.week2lx.presenter;

import com.example.lenovo.week2lx.bean.CartBean;
import com.example.lenovo.week2lx.fragment.FragmentCart;
import com.example.lenovo.week2lx.model.CartModel;
import com.example.lenovo.week2lx.view.IcartView;

import java.util.HashMap;

/**
 * Created by lenovo on 2018/9/8.
 */

public class CartPresenter {
    private IcartView icartView;
    private CartModel cartModel;

    public CartPresenter(FragmentCart icartView) {
        this.icartView = icartView;
        cartModel=new CartModel();
    }
    public void getCart(HashMap<String,String> params, String url){
        cartModel.getCart(params, url, new CartModel.CartCallBack() {
            @Override
            public void success(CartBean cartBean) {
                icartView.success(cartBean);
            }

            @Override
            public void fail(String msg) {
                icartView.failure(msg);
            }
        });
    }
}
