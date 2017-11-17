package com.haulmont.masquerade.restapi;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface Users {
    @GET("entities/sec$User")
    Call<List<User>> all();
}