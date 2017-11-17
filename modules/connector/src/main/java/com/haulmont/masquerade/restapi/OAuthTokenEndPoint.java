package com.haulmont.masquerade.restapi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OAuthTokenEndPoint {
    @FormUrlEncoded
    @POST("oauth/token")
    Call<AccessToken> token(@Field("username") String username, @Field("password") String password,
                            @Field("grant_type") String grantType);
}