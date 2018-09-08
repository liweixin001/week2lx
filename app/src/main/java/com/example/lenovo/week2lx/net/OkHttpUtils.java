package com.example.lenovo.week2lx.net;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler=new Handler();


    private OkHttpUtils(){
       okHttpClient = new OkHttpClient.Builder()
               .writeTimeout(2000, TimeUnit.MICROSECONDS)
               .build();
    }

    public static OkHttpUtils getInstance(){
        if(okHttpUtils == null){
            synchronized (OkHttpUtils.class){
                if(okHttpUtils == null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }
//    public void getData(String url, final OkCallBack okCallBack) {
//        final Request request = new Request.Builder()
//                .get()
//                .url(url)
//                .build();
//        Call call = new OkHttpClient().newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, final IOException e) {
//                if (okCallBack != null) {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            okCallBack.onFailure(e);
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response != null && response.isSuccessful()) {
//                    final String json = response.body().string();
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            okCallBack.onResponse(json);
//                        }
//                    });
//                }
//            }
//        });
//    }


    public void postData(String url, Map<String,String> map, final  RequestCallBack requestCallBack ){
        FormBody.Builder formbodyBuilder = new FormBody.Builder();
        if(map!=null){
            for (String key : map.keySet()) {
                formbodyBuilder.add(key,map.get(key));
            }
        }
        final Request request = new Request.Builder()
                .url(url)
                .post(formbodyBuilder.build())
                .build();

        Call call=new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                if (requestCallBack != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallBack.failure(call, e);
                        }

                    });
                }
            }
            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    final String json=response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                           requestCallBack.onResponse(call, response);
                        }
                    });
                }
            }
        });
    }


    public interface OkCallBack {
        void onFailure(Exception e);

        void onResponse(String json);
    }


}
