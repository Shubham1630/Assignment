package com.android.assignment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

interface APIService {

     @GET("now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
     Call<ResponseBody> getData();


}

