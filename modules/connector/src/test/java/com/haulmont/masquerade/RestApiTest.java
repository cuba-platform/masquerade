package com.haulmont.masquerade;

import com.haulmont.masquerade.restapi.User;
import com.haulmont.masquerade.restapi.UserService;
import okhttp3.ResponseBody;
import org.junit.Test;
import retrofit2.Call;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RestApiTest {
    // see https://futurestud.io/tutorials/oauth-2-on-android-with-retrofit

    @Test
    public void users() throws Exception {
        UserService userService = Connectors.restApi(UserService.class);

        List<User> allUsers = userService.all().execute().body();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
    }

    @Test
    public void createUser() throws Exception {
        UserService userService = Connectors.restApi(UserService.class);

        User user = new User();
        user.setLogin("demo");
        user.setEmail("demo@demo.com");

        Call<ResponseBody> call = userService.create(user);
        call.execute();
    }
}