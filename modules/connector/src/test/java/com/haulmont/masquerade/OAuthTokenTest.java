package com.haulmont.masquerade;

import com.haulmont.masquerade.restapi.User;
import com.haulmont.masquerade.restapi.Users;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OAuthTokenTest {
    @Test
    public void users() throws IOException {
        // see https://futurestud.io/tutorials/oauth-2-on-android-with-retrofit

        Users users = Connectors.restApi(Users.class);

        List<User> allUsers = users.all().execute().body();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
    }
}