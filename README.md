# Retrofit With CallBack File Upload




# You Need To Fallow This Way

* Manifest Permission

      <uses-permission android:name="android.permission.INTERNET"/>
    
* SET Interface to END Point

        @Multipart
        @POST("driver-image-upload")
        Call<ResponseBody> upload(@Part MultipartBody.Part num,
                                  @Part MultipartBody.Part file);
                              
                             
* IN APIClass set Fallowing Things
 
        public static Retrofit retrofitImage;
    
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
    
    
* Set This Things in Your File Upload Place
 
      private void uploadFile() {
      
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
    
    
