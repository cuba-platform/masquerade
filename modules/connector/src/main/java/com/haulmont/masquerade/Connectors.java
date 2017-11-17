package com.haulmont.masquerade;

import com.haulmont.masquerade.jmx.JmxCallHandler;
import com.haulmont.masquerade.jmx.JmxName;
import com.haulmont.masquerade.restapi.AccessToken;
import com.haulmont.masquerade.restapi.OAuthTokenEndPoint;
import com.haulmont.masquerade.restapi.ServiceGenerator;
import retrofit2.Call;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class Connectors {
    public static final String JXM_BASE_ADRESS = ":7777";
    public static final String REST_API_BASE_URL = "http://localhost:8080/app/rest/v2/";

    public static <T> T jmx(Class<T> clazz) {
        return jmx(clazz, new JmxHost(null, null, JXM_BASE_ADRESS));
    }

    @SuppressWarnings("unchecked")
    public static <T> T jmx(Class<T> clazz, JmxHost hostInfo) {
        JmxName jmxName = clazz.getAnnotation(JmxName.class);
        if (jmxName == null) {
            throw new RuntimeException("There is no @JmxName annotation for " + clazz);
        }
        if ("".equals(jmxName.value())) {
            throw new RuntimeException("JmxName.value is empty for " + clazz);
        }

        return (T) Proxy.newProxyInstance(Connectors.class.getClassLoader(), new Class[]{clazz},
                new JmxCallHandler(hostInfo, jmxName.value()));
    }

    public static <T> T restApi(Class<T> clazz) {
        return restApi(clazz, new RestApiHost("admin", "admin", REST_API_BASE_URL));
    }

    @SuppressWarnings("unchecked")
    public static <T> T restApi(Class<T> clazz, RestApiHost hostInfo) {
        // authenticate
        OAuthTokenEndPoint tokenEndPoint = ServiceGenerator.createService(hostInfo.getBaseUrl(),
                OAuthTokenEndPoint.class, hostInfo.getClientId(), hostInfo.getClientSecret());
        Call<AccessToken> token = tokenEndPoint.token(hostInfo.getUser(), hostInfo.getPassword(), hostInfo
                .getGrantType());

        AccessToken accessToken;
        try {
            accessToken = token.execute().body();
        } catch (IOException e) {
            throw new RuntimeException("Unable to obtain OAuth token");
        }

        return ServiceGenerator.createService(hostInfo.getBaseUrl(), clazz, accessToken);
    }

    public static class JmxHost {
        private String address;
        private String user;
        private String password;

        public JmxHost(String user, String password, String address) {
            this.user = user;
            this.password = password;
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class RestApiHost {
        private String baseUrl;
        private String user;
        private String password;

        private String clientId = "client";
        private String clientSecret = "secret";
        private String grantType = "password";

        public RestApiHost(String user, String password, String baseUrl) {
            this.user = user;
            this.password = password;
            this.baseUrl = baseUrl;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String getGrantType() {
            return grantType;
        }

        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }
    }
}