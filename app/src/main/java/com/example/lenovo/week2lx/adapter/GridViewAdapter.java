package com.example.lenovo.week2lx.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.week2lx.R;
import com.example.lenovo.week2lx.bean.HomeBean;

import java.util.List;

/**
 * Created by lenovo on 2018/9/8.
 */

public class GridViewAdapter extends BaseAdapter{
    private Context context;
    List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist;
    private String imafes;

    public GridViewAdapter(Context context, List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist) {
        this.context = context;
        this.miaoshalist = miaoshalist;
    }

    @Override
    public int getCount() {
        return miaoshalist.size();
    }

    @Override
    public Object getItem(int i) {
        return miaoshalist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = View.inflate(context, R.layout.item_girl_gridview, null);
        ImageView imageView = (ImageView) view1.findViewById(R.id.iv_cover);
        TextView textView = (TextView) view1.findViewById(R.id.tv_title);
        String[] imageUrls = miaoshalist.get(i).getImages().split("\\|");
        if(imageUrls!=null&&imageUrls.length>0){
            Glide.with(context).load(imageUrls[0]).into(imageView);

        }
        textView.setText(miaoshalist.get(i).getTitle());
        return view1;
    }

}
