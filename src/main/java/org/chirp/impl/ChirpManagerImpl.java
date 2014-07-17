package org.chirp.impl;

import org.chirp.*;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChirpManagerImpl implements ChirpManager {

    private Map<String,Chirper> chirperMap;
    private ChirpReceiver chirpReceiver;
    private ChirpBroadcaster chirpBroadcaster;
    private Chirper chirper;

    public ChirpManagerImpl(Chirper chirper,ChirpBroadcaster chirpBroadcaster, ChirpReceiver chirpReceiver){
        this.chirper = chirper;
        this.chirpBroadcaster = chirpBroadcaster;
        this.chirpReceiver = chirpReceiver;
        this.chirpReceiver.registerChirpObserver(this);
    }




    @Override
    public void startChirping() {
        Thread thread = new Thread(this.chirpReceiver);
        thread.setDaemon(true);
        thread.start();
        this.chirpBroadcaster.publish(this.chirper);
        this.chirpBroadcaster.discover(this.chirper);
    }

    @Override
    public void stop() {
        this.chirpBroadcaster.shutdown(this.chirper);
        this.chirpReceiver.stop();
    }

    @Override
    public Set<String> listChirpers() {
        return this.chirperMap.keySet();
    }

    @Override
    public Chirper fetchChirper(String name) {
        if(this.chirper.getName().compareTo(name) == 0){
            return this.chirper;
        }else if(this.chirperMap.containsKey(name)){
            return this.chirperMap.get(name);
        }else{
            this.chirpBroadcaster.discover(this.chirper,name);
            for(int i = 0 ; i < 10 ; i++){
                if(this.chirperMap.containsKey(name)){
                    return this.chirperMap.get(name);
                }else{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //TODO LOG
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
            return null;
        }

    }

    @Override
    public void notify(Chirp chirp) {
        if(chirp.getSender().compareTo(this.chirper.getName()) != 0){
            if(chirp.getMethod().compareTo("PUBLISH") == 0){
                this.chirperMap.put(chirp.getName(),chirp.getChirper());
            }else if(chirp.getMethod().compareTo("STOP") == 0){
                this.chirperMap.remove(chirp.getName());
            }else if(chirp.getMethod().compareTo("DISCOVER") == 0){
                if(chirp.getName().trim().compareTo("") == 0 || chirp.getName().trim().compareTo(this.chirper.getName()) == 0 ){
                    this.chirpBroadcaster.discover(this.chirper);
                }
            }else{

            }

        }
    }
}
