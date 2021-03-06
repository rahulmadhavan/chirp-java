package org.chirp.impl;

import org.chirp.Chirp;
import org.chirp.ChirpBroadcaster;
import org.chirp.Chirper;
import org.chirp.config.ChirpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 16/07/14
 * Time: 5:04 PM
 *
 */
public class ChirpBroadcasterImpl implements ChirpBroadcaster {

    private static Logger logger = LoggerFactory.getLogger(ChirpBroadcasterImpl.class);
    private String multiCastAddress;
    private int multiCastPort;


    public ChirpBroadcasterImpl(String multiCastAddress, int multiCastPort){
        this.multiCastAddress = multiCastAddress;
        this.multiCastPort = multiCastPort;
    }

    private void broadcast(String message){

        byte[] buf = message.getBytes();
        DatagramPacket packet = null;
        MulticastSocket socket = null;
        try {

            socket = new MulticastSocket();
            InetAddress group = InetAddress.getByName(this.multiCastAddress);
            packet = new DatagramPacket(buf, buf.length, group, this.multiCastPort);
            socket.send(packet);
            Thread.sleep(5000);


        } catch (IOException e) {
            logger.error("IO EXCEPTION IN CHIRP BROADCASTER",e);
        } catch (InterruptedException e) {
            logger.error("INTERRUPTED IN CHIRP BROADCASTER",e);
        }finally {
            if(null != socket)
                socket.close();
        }
    }

    private void broadcast(Chirp chirp){
        logger.debug("BROADCASTING CHIRP ---- {}", chirp.toString());
        broadcast(chirp.toString());
    }


    @Override
    public void publish(Chirper chirper) {
        Chirp chirp = new Chirp.ChirpBuilder(ChirpMethod.PUBLISH,chirper).build();
        broadcast(chirp);
    }

    @Override
    public void shutdown(Chirper chirper) {
        Chirp chirp = new Chirp.ChirpBuilder(ChirpMethod.STOP,chirper).build();
        broadcast(chirp);
    }

    @Override
    public void discover(Chirper chirper) {
        Chirp chirp = new Chirp.ChirpBuilder()
                .setMethod(ChirpMethod.DISCOVER)
                .setName("")
                .setSender(chirper.getName())
                .build();
        broadcast(chirp);
    }

    @Override
    public void discover(Chirper chirper,String name) {
        Chirp chirp = new Chirp.ChirpBuilder()
                .setMethod(ChirpMethod.DISCOVER)
                .setName(name)
                .setSender(chirper.getName())
                .build();
        broadcast(chirp);
    }
}
