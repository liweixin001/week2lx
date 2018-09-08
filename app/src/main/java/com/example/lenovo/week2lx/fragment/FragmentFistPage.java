package com.example.lenovo.week2lx.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.week2lx.R;
import com.example.lenovo.week2lx.adapter.RecyclerAdapter;
import com.example.lenovo.week2lx.bean.HomeBean;
import com.example.lenovo.week2lx.common.Api;
import com.example.lenovo.week2lx.net.OkHttpUtils;
import com.example.lenovo.week2lx.net.RequestCallBack;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/9/8.
 */

public class FragmentFistPage extends Fragment{
//    @BindView(R.id.page_xrcv)
//    XRecyclerView xRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private Handler handler=new Handler();
    private List<HomeBean.DataBean.FenleiBean> fenlei;
    private List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> tuijianlist;
    private RecyclerAdapter homeAdapter;
    private HomeBean homeBean;
    private int page=1;
    private View view;
    private XRecyclerView xRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.firstpage_fragment, container, false);

//        ButterKnife.bind(view);
        initView();
        initDatas();

        return view;
    }

    private void initView() {
        xRecyclerView = view.findViewById(R.id.page_xrcv);
    }

    private void initDatas() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setLoadingMoreEnabled(true);
        getHome();
    }


    public void getHome() {
        OkHttpUtils.getInstance().postData(Api.HOME_URL, new HashMap<String, String>(), new RequestCallBack() {
            @Override
            public void failure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                if (200==response.code()){

                    try {
                        String result = response.body().string();
                        if (!TextUtils.isEmpty(result)){
                            jiexi(result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }




            private void jiexi(String result) {
                HomeBean homeBean=new Gson().fromJson(result,HomeBean.class);
                fenlei = homeBean.getData().getFenlei();
                miaoshalist = homeBean.getData().getMiaosha().getList();
                tuijianlist = homeBean.getData().getTuijian().getList();
                if(homeBean !=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            fillDatas();
                        }
                    });
                }
            }

        });
    }

    private void fillDatas() {
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (page==1){
            homeAdapter = new RecyclerAdapter(getActivity(), homeBean,fenlei,miaoshalist,tuijianlist);
            xRecyclerView.setAdapter(homeAdapter);
            xRecyclerView.refreshComplete();//刷新完成
        }

    }
}
