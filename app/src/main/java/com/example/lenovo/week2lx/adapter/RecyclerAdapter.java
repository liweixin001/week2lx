package com.example.lenovo.week2lx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.week2lx.R;
import com.example.lenovo.week2lx.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/9/8.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //定义四种类型
    private final int BANNER_VIEW_TYPE = 0;//轮播图
    private final int CLASSES_VIEW_TYPE = 1;//频道
    private final int GIRL_VIEW_TYPE = 2;
    private final int TUIJIAN_VIEW_TYPE=3;

    private Context context;
    private HomeBean homeBean;
    private List<String> imageList;
    private List<HomeBean.DataBean.FenleiBean> fenlei;
    private List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> tuijianlist;
    private View view;
    private BannerHolder banner;
    private ChannelHolder channel;

    public RecyclerAdapter(Context context, HomeBean homeBean, List<HomeBean.DataBean.FenleiBean> fenlei, List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist, List<HomeBean.DataBean.TuijianBean.ListBeanX> tuijianlist) {

        this.context = context;
        this.homeBean = homeBean;
        this.imageList = imageList;
        this.fenlei = fenlei;
        this.miaoshalist = miaoshalist;
        this.tuijianlist = tuijianlist;
        for (HomeBean.DataBean.BannerBean bannerBean : homeBean.getData().getBanner()) {
            imageList.add(bannerBean.getIcon());
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {//第0个位置是轮播图
            return BANNER_VIEW_TYPE;
        } else if (position == 1) {
            return CLASSES_VIEW_TYPE;
        }else if (position == 2) {
            return GIRL_VIEW_TYPE;
        }else if (position == 3) {
            return TUIJIAN_VIEW_TYPE;
        } else {
            return super.getItemViewType(position);
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==BANNER_VIEW_TYPE){
            view = LayoutInflater.from(context).inflate(R.layout.banner_layout,parent,false);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        }else if (viewType == CLASSES_VIEW_TYPE) {//分类
            view = LayoutInflater.from(context).inflate(R.layout.item_channel,parent,false);
            ChannelHolder channelHolder = new ChannelHolder(view);
            return channelHolder;
        }else if (viewType == GIRL_VIEW_TYPE) {//秒杀
            view = LayoutInflater.from(context).inflate(R.layout.item_girl,parent,false);
            return new GirlHolder(view);
        }else if (viewType == TUIJIAN_VIEW_TYPE) {//推荐
            view = LayoutInflater.from(context).inflate(R.layout.recommend_layout,parent,false);
            return new TuiJianHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerHolder) {
            BannerHolder bannerHolder = (BannerHolder) holder;
            setBanner(bannerHolder);
        } else if (holder instanceof ChannelHolder) {
            ChannelHolder channelHolder = (ChannelHolder) holder;
            setChannel(channelHolder);
        }else if(holder instanceof GirlHolder){
            GirlHolder girlHolder= (GirlHolder) holder;

            GridViewAdapter adapter = new GridViewAdapter(context,miaoshalist);
            girlHolder.gridView.setAdapter(adapter);
        }else if(holder instanceof  TuiJianHolder) {
            TuiJianHolder tuiJianHolder = (TuiJianHolder) holder;
            TJGridViewAdapter adapter = new TJGridViewAdapter(context, tuijianlist);
            tuiJianHolder.tuijian_gridview.setAdapter(adapter);
            tuiJianHolder.tuijian_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(context, tuijianlist.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public void setBanner(BannerHolder bannerHolder) {
        bannerHolder.page_banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerHolder.page_banner.setImages(imageList);
        //设置banner动画效果
        bannerHolder.page_banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//            bannerHolder.banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        bannerHolder.page_banner.isAutoPlay(true);
        //设置轮播时间
        bannerHolder.page_banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        bannerHolder.page_banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        bannerHolder.page_banner.start();
    }
    @BindView(R.id.iv_logo)
    ImageView ivlogo;
    @BindView(R.id.tv_channel)
    TextView tvChannel;
    public void setChannel(ChannelHolder channel) {

        for (int i = 0; i < fenlei.size(); i++) {
            View view=View.inflate(context,R.layout.item_channel_view,null);
            Glide.with(context).load(fenlei.get(i).getIcon().toString()).into(ivlogo);
            tvChannel.setText(fenlei.get(i).getName().toString());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(24,0,24,0);
            view.setTag(i);
            final int finalI = i;

            channel.linearLayout.addView(view);
        }
    }

    public static class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.page_banner)
        Banner page_banner;
        public BannerHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ChannelHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_channel)
        LinearLayout linearLayout;
        public ChannelHolder(View view) {
            super(view);

        }
    }

    public static class GirlHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gridview)
        GridView gridView;
        public GirlHolder(View view) {
            super(view);
        }
    }

    public static class TuiJianHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tuijian_gridview)
        GridView tuijian_gridview;
        public TuiJianHolder(View view) {
            super(view);
        }
    }

    private class GlideImageLoader implements ImageLoaderInterface<ImageView> {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            ImageView imageView = new ImageView(context);
            return imageView;
        }
    }
}
