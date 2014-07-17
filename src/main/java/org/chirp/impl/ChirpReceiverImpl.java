package org.chirp.impl;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.chirp.Chirp;
import org.chirp.ChirpReceiver;
import org.chirp.ChirpObserver;


/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChirpReceiverImpl implements ChirpReceiver{

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
                System.out.println("Message : " + received);
                Thread.sleep(1000);

            }

            socket.leaveGroup(group);

        }catch (IOException e){
            //TODO: Log Exception
            e.printStackTrace();
        } catch (InterruptedException e) {
            //TODO: Log Exception
            e.printStackTrace();
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
        System.out.println("Receiver Stopped");
    }


    @Override
    public Chirp receive(String message) {
        Gson gson = new Gson();
        Chirp chirp = gson.fromJson(message,Chirp.class);
        if(chirpObserver != null){
            chirpObserver.notify(chirp);
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
