package com.aruna.retrofit;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by aruna on 1/1/18.
 */

public interface ApiInterface {

    @POST("readContact.php")
    Call<List<Contact>> getContacts();

    @Multipart
    @POST("driver-image-upload")
    Call<ResponseBody> upload(@Part MultipartBody.Part num,
                              @Part MultipartBody.Part file);
}
