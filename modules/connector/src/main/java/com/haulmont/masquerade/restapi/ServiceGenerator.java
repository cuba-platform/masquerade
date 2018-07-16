/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.masquerade.restapi;

import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

public class ServiceGenerator {

    private static LoadingCache<String, Retrofit.Builder> builders = CacheBuilder.newBuilder()
            .build(new CacheLoader<String, Retrofit.Builder>() {
                @Override
                public Retrofit.Builder load(@Nonnull String baseUrl) {
                    return new Retrofit.Builder()
                                    .baseUrl(baseUrl)
                                    .addConverterFactory(GsonConverterFactory.create());
                }
            });

    /**
     * Creates service proxy with client BASIC authentication.
     *
     * @param baseUrl      base URL
     * @param serviceClass service class
     * @param clientId     client id
     * @param clientSecret client secret
     * @param <S>          type of service class
     * @return service proxy instance
     */
    public static <S> S createService(String baseUrl, Class<S> serviceClass, String clientId, String clientSecret) {
        if (!Strings.isNullOrEmpty(clientId)
                && !Strings.isNullOrEmpty(clientSecret)) {
            String authToken = Credentials.basic(clientId, clientSecret);
            return createService(baseUrl, serviceClass, authToken);
        }

        return createService(baseUrl, serviceClass);
    }

    /**
     * Creates service proxy with REST-API token authentication.
     *
     * @param baseUrl      base URL
     * @param serviceClass service class
     * @param accessToken  access token object
     * @param <S>          type of service class
     * @return service proxy instance
     */
    public static <S> S createService(String baseUrl, Class<S> serviceClass, AccessToken accessToken) {
        checkArgument(accessToken != null, "accessToken should not be null");

        return createService(baseUrl, serviceClass, accessToken.toAuthorizationToken());
    }

    /**
     * Creates service proxy with REST-API token authentication.
     *
     * @param baseUrl      base URL
     * @param serviceClass service class
     * @param authToken    authentication token
     * @param <S>          type of service class
     * @return service proxy instance
     */
    public static <S> S createService(String baseUrl, Class<S> serviceClass, final String authToken) {
        Retrofit.Builder builder = builders.getUnchecked(baseUrl);

        if (!Strings.isNullOrEmpty(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(interceptor);

            builder.client(httpClient.build());
            Retrofit retrofit = builder.build();
            return retrofit.create(serviceClass);
        }

        return builder.build().create(serviceClass);
    }

    /**
     * Creates service proxy without authentication.
     *
     * @param baseUrl      base URL
     * @param serviceClass service class
     * @param <S>          type of service class
     * @return service proxy instance
     */
    public static <S> S createService(String baseUrl, Class<S> serviceClass) {
        Retrofit.Builder builder = builders.getUnchecked(baseUrl);

        return builder.build().create(serviceClass);
    }
}