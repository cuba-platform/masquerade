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

package com.haulmont.masquerade;

import com.haulmont.masquerade.restapi.User;
import com.haulmont.masquerade.restapi.UserService;
import okhttp3.ResponseBody;
import org.junit.Ignore;
import org.junit.Test;
import retrofit2.Call;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
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