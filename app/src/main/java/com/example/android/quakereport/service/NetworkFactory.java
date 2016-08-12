package com.example.android.quakereport.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by htenjo on 8/12/16.
 */
public class NetworkFactory{
    private static NetworkFactory instance = new NetworkFactory();

    private NetworkFactory(){
    }

    public static NetworkFactory getInstance(){
        return instance;
    }

    public static <T> T buildService(Class<T> serviceClass, String serviceBaseUrl){
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serviceBaseUrl)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T service = retrofit.create(serviceClass);
        return service;
    }
}
