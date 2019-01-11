package com.aruna.retrofit;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aruna on 1/1/18.
 */

public class ApiClient {

    Result result;

    public static final String BASE_URL = "http://192.168.8.100/android/";

    public static Retrofit retrofit;
    public static Retrofit retrofitImage;

    public ApiClient(Result result) {
        this.result = result;
    }

    public static Retrofit getApiClientImage() {

        if (retrofitImage == null) {

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.connectTimeout(200, TimeUnit.SECONDS);
            client.readTimeout(200, TimeUnit.SECONDS);
            client.writeTimeout(200, TimeUnit.SECONDS);

            /*retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();*/
            retrofitImage = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofitImage;
    }

    public static Retrofit getApiClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public void result(Call<List<Contact>> call) {

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {

                boolean success = response.isSuccessful();

                Log.e("message", String.valueOf(response.message()));
                result.onResponse(response);
            }


            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                result.onFailure(t);
            }
        });
    }

    public interface Result {
        void onResponse(Response<List<Contact>> response);
        void onFailure(Throwable t);
    }
}

