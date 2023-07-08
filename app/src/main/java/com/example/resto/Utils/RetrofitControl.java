package com.example.resto.Utils;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitControl {

    public static retrofit2.Retrofit getRetrofit(String url){
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                        .baseUrl(url)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

        return  retrofit;
    }

}
