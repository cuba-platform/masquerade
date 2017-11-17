package com.haulmont.masquerade.restapi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface UserService {
    @GET("entities/sec$User")
    Call<List<User>> all();

    @POST("entities/sec$User")
    Call<ResponseBody> create(@Body User user);
}