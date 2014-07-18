package org.chirp.impl;

import com.google.gson.Gson;
import org.chirp.Chirp;
import org.chirp.ChirpBroadcaster;
import org.chirp.Chirper;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChirpBroadcasterImpl implements ChirpBroadcaster {



    private void broadcast(String message){

        byte[] buf = message.getBytes();
        DatagramPacket packet = null;
        MulticastSocket socket = null;
        try {

            socket = new MulticastSocket();
            InetAddress group = InetAddress.getByName("224.1.1.4");
            packet = new DatagramPacket(buf, buf.length, group, 9292);
            socket.send(packet);
            Thread.sleep(5000);

            //TODO log exception
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            if(null != socket)
                socket.close();
        }
    }

    private void broadcast(Chirp chirp){
        //TODO remove GSON usage from here
        Gson gson = new Gson();
        System.out.println("BROADCASTING CHIRP ---- " + chirp.toString());
        broadcast(gson.toJson(chirp));
    }


    @Override
    public void publish(Chirper chirper) {
        Chirp chirp = new Chirp.ChirpBuilder("PUBLISH",chirper).build();
        broadcast(chirp);
    }

    @Override
    public void shutdown(Chirper chirper) {
        Chirp chirp = new Chirp.ChirpBuilder("STOP",chirper).build();
        broadcast(chirp);
    }

    @Override
    public void discover(Chirper chirper) {
        Chirp chirp = new Chirp.ChirpBuilder()
                .setMethod("DISCOVER")
                .setName("")
                .setSender(chirper.getName())
                .build();
        broadcast(chirp);
    }

    @Override
    public void discover(Chirper chirper,String name) {
        Chirp chirp = new Chirp.ChirpBuilder()
                .setMethod("DISCOVER")
                .setName(name)
                .setSender(chirper.getName())
                .build();
        broadcast(chirp);
    }
}
