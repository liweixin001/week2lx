package com.example.lenovo.week2lx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.week2lx.R;
import com.example.lenovo.week2lx.bean.CartBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/9/8.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>implements CheckListener{
    private CheckListener checkListener;
    private Context context;
    private List<CartBean.DataBean> cartList;
    private CartAllCheckboxlistener allCheckboxlistener;

    public CartAdapter(Context context, List<CartBean.DataBean> cartList) {
        this.context = context;
        this.cartList = cartList;
    }
    public void addPageData(List<CartBean.DataBean> list){
        if (cartList!=null){
            cartList.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void setAllCheckboxlistener(CartAllCheckboxlistener allCheckboxlistener) {
        this.allCheckboxlistener = allCheckboxlistener;
    }
    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void notifyParent() {

    }
    @BindView(R.id.sellerCheckbox)
    CheckBox checkBox;
    @BindView(R.id.sellerNameTv)
    TextView nameTv;
    @BindView(R.id.productXRV)
    RecyclerView proudXRV;
    public class CartViewHolder extends RecyclerView.ViewHolder {


        public CartViewHolder(View itemView) {
            super(itemView);
        }
    }
}
