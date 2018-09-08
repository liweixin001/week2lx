package com.example.lenovo.week2lx.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/8/24.
 */

public interface RequestCallBack {
void failure(Call call, IOException e);
void onResponse(Call call, Response response);
}
