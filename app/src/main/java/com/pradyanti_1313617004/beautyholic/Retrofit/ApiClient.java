package com.pradyanti_1313617004.beautyholic.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String BASE_URL = "https://makeup-api.herokuapp.com/api/v1/";
    private static Retrofit retrofit;
    public static ApiInterface apiInterface() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
