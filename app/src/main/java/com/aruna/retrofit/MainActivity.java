package com.aruna.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ApiClient.Result{

    private ApiInterface apiInterface;

    File image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Contact>> call = apiInterface.getContacts();

        ApiClient apiClient = new ApiClient(this);
        apiClient.result(call);*/

        uploadFile();

    }

    private void uploadFile() {
        /*String driver_number = sharedPreference.getValue(context, Constants.MOBILE_NO);
        RequestBody reqFile = RequestBody.create(MediaType.parse("application/json"), image);
        RequestBody number = RequestBody.create(MediaType.parse("application/json"), driver_number);

        Call<ImageResponse> call = apiInterface.updateProfilePhotoProcess(number,reqFile);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {

                ImageResponse userModelResponse = response.body();
//                UserModel userModel = userModelResponse.getUserModel();

                Log.d("MainActivity","user image = "+userModelResponse.getSuccess());

            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());


            }
        });*/


        String driver_number = "071663971";
        // create upload service client
        ApiInterface service =
                ApiClient.getApiClientImage().create(ApiInterface.class);

        MediaType MEDIA_TYPE_MARKDOWN
                = MediaType.parse("application/json");
        File file = image;

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MEDIA_TYPE_MARKDOWN,
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("driver_image", file.getName(), requestFile);

        MultipartBody.Part num =
                MultipartBody.Part.createFormData("driver_mobile", driver_number);

        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, driver_number);

        RequestBody img =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, image);

        // finally, execute the request
        Call<ResponseBody> call = service.upload(num, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();

                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                uploadFile();
            }
        });
    }


    @Override
    public void onResponse(Response<List<Contact>> response) {
        List<Contact> contacts = response.body();

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
