package org.chirp;

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

    @Test
    public void testChirpDeserialization()
    {


        String chirpString = " {\"method\":\"PUBLISH\",\"name\":\"Chirper1\",\"uri\":\"chirper1.org\",\"port\":9090,\"protocol\":\"http\",\"config\":{},\"sender\":\"Chirper1\"} ";
        Chirp chirp = new Chirp.ChirpBuilder()
                .setMethod("TEST")
                .setName("TEST_NAME")
                .setPort(10001)
                .build();

        Gson gson = new Gson();
        Chirp convertedChirp = gson.fromJson(chirpString,Chirp.class);

        assertEquals("Chirper1",convertedChirp.getName());
        assertEquals(9090,convertedChirp.getPort());
        assertEquals("PUBLISH",convertedChirp.getMethod());
        assertEquals("http",convertedChirp.getProtocol());


    }




}
