package com.pradyanti_1313617004.beautyholic.Retrofit;

import com.pradyanti_1313617004.beautyholic.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("products.json?")
    Call<List<Product>> getProductData(@Query("product_type") String product_type);

}
