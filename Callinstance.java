package com.android.assignment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public abstract class Callinstance {
    public static final String baseurl=" https://api.themoviedb.org/3/movie/";
public static Call<ResponseBody> getCall(){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl( baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    return  retrofit.create(APIService.class).getData(); }
}
