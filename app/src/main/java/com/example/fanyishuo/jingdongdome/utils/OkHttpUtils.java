package com.example.fanyishuo.jingdongdome.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by fanyishuo on 2017/9/12.
 */

public class OkHttpUtils {
     public static void sendOkHttpRequest(String address, Callback callback){
             OkHttpClient client = new OkHttpClient();
             Request request = new Request.Builder()
                     .url(address)
                     .build();
             client.newCall(request).enqueue(callback);
         }

}
