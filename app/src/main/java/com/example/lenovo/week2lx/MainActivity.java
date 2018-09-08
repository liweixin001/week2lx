package com.example.lenovo.week2lx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.week2lx.fragment.FragmentCart;
import com.example.lenovo.week2lx.fragment.FragmentFistPage;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomTabBar btb = findViewById(R.id.bottomTabBar);
        btb.init(getSupportFragmentManager())//初始化方法布局管理
                .setFontSize(0)//设置文字大小
                //参数1：选中后的颜色，参数2：选中前的颜色
                //参数1：文字内容。参数2：导航图片。参数3：切换哪个fragment类
                .setImgSize(150, 150)
                .setTabPadding(5, 1, 1)
                .addTabItem("首页",R.drawable.black_background, FragmentFistPage.class)
//                .addTabItem("分类", FragmentFenLei.class)
                .addTabItem("购物车", R.drawable.black_background,FragmentCart.class)
                .isShowDivider(false);

    }

}
