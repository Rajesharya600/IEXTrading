package com.example.ritz.iextrading.Retrofit;

import com.example.ritz.iextrading.Data.StockData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Ritz on 18-11-2018.
 */

public interface APIInterface {

    @GET("/1.0/stock/{symbol}/batch?types=quote,company")
    abstract Call<StockData> getAllData(@Path("symbol") String stocksymbolname);
}
