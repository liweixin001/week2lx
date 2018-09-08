package com.example.lenovo.week2lx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.week2lx.R;
import com.example.lenovo.week2lx.adapter.CartAdapter;
import com.example.lenovo.week2lx.adapter.CartAllCheckboxlistener;
import com.example.lenovo.week2lx.bean.CartBean;
import com.example.lenovo.week2lx.common.Api;
import com.example.lenovo.week2lx.presenter.CartPresenter;
import com.example.lenovo.week2lx.view.IcartView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/9/8.
 */

public class FragmentCart extends Fragment implements IcartView,CartAllCheckboxlistener {
    @BindView(R.id.allCheckbox)
    CheckBox checkBox;
    @BindView(R.id.caetRv)
    XRecyclerView xrecyclerView;
    @BindView(R.id.totalPriceTV)
    TextView totalPricetv;
    private List<CartBean.DataBean> list;

    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cart_fragment, container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        xrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", "71");
        CartPresenter cartPresenter = new CartPresenter(this);
        cartPresenter.getCart(params, Api.GETCARTS);
    }

    @Override
    public void notifyAllCheckboxStatus() {

    }

    @Override
    public void success(CartBean cartBean) {
        if (cartBean != null && cartBean.getData() != null) {
            list = cartBean.getData();
            cartAdapter = new CartAdapter(getContext(), list);
            xrecyclerView.setAdapter(cartAdapter);
            cartAdapter.setAllCheckboxlistener(this);
        }


    }

    @Override
    public void failure(String msg) {

    }
}