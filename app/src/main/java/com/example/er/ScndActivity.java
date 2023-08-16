package com.example.er;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScndActivity extends AppCompatActivity {
    private List<School> schools;
    private RecyclerView recyclerView;
    private SchoolAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        schools = new ArrayList<>();
        adapter = new SchoolAdapter(schools);
        recyclerView.setAdapter(adapter);

        fetchSchoolData();
    }

    private void fetchSchoolData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://indonesia-school-list.p.rapidapi.com/") // Base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<School>> call = apiService.getSekolahByKodeKecamatan("574a0bbcfdmshbd6a14e8d93ff69p1adb2ejsna1e1f33816f5", "020511");
        call.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<School> apiResponse = response.body();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            schools.addAll(apiResponse);
                            adapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    Log.e("FetchSchoolData", "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {
                Log.e("FetchSchoolData", "Error fetching data: " + t.getMessage());
            }
        });
    }

    @Nullable
    private List<School> parseJsonResponse(String json) {
        Gson gson = new Gson();
        Type schoolListType = new TypeToken<List<School>>() {}.getType();
        return gson.fromJson(json, schoolListType);
    }
}
