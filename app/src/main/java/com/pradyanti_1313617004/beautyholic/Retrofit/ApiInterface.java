package com.pradyanti_1313617004.beautyholic.Retrofit;

import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.Model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("products.json?product_type=blush")
    Call<List<Product>> getProductData();


}
