package org.chirp.impl;

import com.google.gson.Gson;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChirpTest {

    @Test
    public void testChirpCreationAndSerialization()
    {

        Chirp chirp = new Chirp.ChirpBuilder()
                .setMethod("TEST")
                .setName("TEST_NAME")
                .setPort(10001)
                .build();

        Gson gson = new Gson();
        String jsonChirp = gson.toJson(chirp);
        Chirp convertedChirp = gson.fromJson(jsonChirp,Chirp.class);

        assertEquals(chirp.getName(),convertedChirp.getName());
        assertEquals(chirp.getPort(),convertedChirp.getPort());
        assertEquals(chirp.getMethod(),convertedChirp.getMethod());


    }

}
