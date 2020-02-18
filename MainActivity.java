package com.android.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.assignment.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
   List<String> image_url,movie_info,overview_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overview_title=new ArrayList<>();
        movie_info=new ArrayList<>();
        image_url=new ArrayList<>();
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loaddata();
    }
    public void loaddata()  {
        Callinstance.getCall().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{

                    String data=response.body().string();
                    JSONObject jsonObject=new JSONObject(data);
                    JSONArray jsonArray=jsonObject.optJSONArray("results");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    if(jsonArray.getJSONObject(i).getInt("popularity")>100){
                                        image_url.add("https://image.tmdb.org/t/p/original"+jsonArray.getJSONObject(i).getString("backdrop_path"));
                                        overview_title.add("");
                                        movie_info.add(jsonArray.getJSONObject(i).getString("overview"));

                                    }else{
                                        image_url.add("https://image.tmdb.org/t/p/w342"+jsonArray.getJSONObject(i).getString("poster_path"));
                                        overview_title.add(jsonArray.getJSONObject(i).getString("original_title").toUpperCase()+ '\n'+
                                                jsonArray.getJSONObject(i).getString("overview")) ;
                                        movie_info.add(jsonArray.getJSONObject(i).getString("original_title").toUpperCase()+ '\n'+
                                                jsonArray.getJSONObject(i).getString("overview")) ;

                                    }
                                }
                    mAdapter = new Adapter(getApplicationContext(), image_url,overview_title ,movie_info);
                    mRecyclerView.setAdapter(mAdapter);



                            } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getCause().toString() , Toast.LENGTH_SHORT).show();

            }


        });





            }
    }

