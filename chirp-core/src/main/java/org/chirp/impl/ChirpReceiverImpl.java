package org.chirp.impl;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.chirp.Chirp;
import org.chirp.ChirpReceiver;
import org.chirp.ChirpObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChirpReceiverImpl implements ChirpReceiver{

    private static Logger logger = LoggerFactory.getLogger(ChirpReceiverImpl.class);

    private boolean stopped;
    private ChirpObserver chirpObserver;

    public ChirpReceiverImpl(){
        this.stopped = false;

    }

    public ChirpObserver getChirpObserver() {
        return chirpObserver;
    }

    public void setChirpObserver(ChirpObserver chirpObserver) {
        this.chirpObserver = chirpObserver;
    }

    @Override
    public void run() {

        MulticastSocket socket = null;
        InetAddress group;
        DatagramPacket packet;

        try{
            socket = new MulticastSocket(9292);
            group = InetAddress.getByName("224.1.1.4");
            socket.joinGroup(group);


            while(!isStopped()){
                byte[] buf = new byte[10240];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String received = new String(packet.getData());
                this.receive(received.trim());
                Thread.sleep(1000);

            }

            socket.leaveGroup(group);

        }catch (IOException e){
            logger.error("IO EXCEPTION IN CHIRP RECEIVER",e);
        } catch (InterruptedException e) {
            logger.error("INTERRUPTED IN CHIRP RECEIVER", e);
        } finally {

            if(null != socket){
                socket.close();
            }

        }

    }

    @Override
    public void registerChirpObserver(ChirpObserver chirpObserver) {
        this.chirpObserver = chirpObserver;
    }

    public void stop(){
        setStopped(true);
        logger.info("Receiver Stopped");
    }


    @Override
    public Chirp receive(String message) {
        Gson gson = new Gson();
        Chirp chirp = gson.fromJson(message,Chirp.class);
        if(chirpObserver != null){
            chirpObserver.notify(chirp);
        }else{
            logger.warn("NO OBSERVER SET IN RECEIVER");
        }
        return chirp;
    }


    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }



}
