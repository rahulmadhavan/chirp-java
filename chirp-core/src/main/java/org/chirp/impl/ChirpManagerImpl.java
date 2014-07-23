package org.chirp.impl;

import org.chirp.*;
import org.chirp.config.ChirpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 16/07/14
 * Time: 7:03 PM
 *
 */
public class ChirpManagerImpl implements ChirpManager {

    private static Logger logger = LoggerFactory.getLogger(ChirpBroadcasterImpl.class);

    private Map<String,Chirper> chirperMap;
    private ChirpReceiver chirpReceiver;
    private ChirpBroadcaster chirpBroadcaster;
    private Chirper chirper;

    public ChirpManagerImpl(Chirper chirper,ChirpBroadcaster chirpBroadcaster, ChirpReceiver chirpReceiver){
        this.chirper = chirper;
        this.chirperMap = new HashMap<String, Chirper>();
        this.chirpBroadcaster = chirpBroadcaster;
        this.chirpReceiver = chirpReceiver;
    }

    @Override
    public void startChirping() {
        Thread thread = new Thread(this.chirpReceiver);
        thread.setDaemon(true);
        thread.start();
        this.chirpReceiver.registerChirpObserver(this);
        this.chirpBroadcaster.publish(this.chirper);
        this.chirpBroadcaster.discover(this.chirper);
    }

    @Override
    public void stop() {
        this.chirpBroadcaster.shutdown(this.chirper);
        this.chirpReceiver.stop();
    }

    @Override
    public Map<String,Chirper> listChirpers() {
        return this.chirperMap;
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
                        logger.debug("CHIRP MANAGER INTERRUPTED WHILE FETCHING", e);
                    }
                }
            }
            return null;
        }

    }

    @Override
    public Chirper getChirper() {
        return this.chirper;
    }

    @Override
    public void notify(Chirp chirp) {
        logger.debug("RECEIVED CHIRP ---- {}", chirp.toString());
        if(chirp.getSender().compareTo(this.chirper.getName()) != 0){
            if(chirp.getMethod() == ChirpMethod.PUBLISH){
                this.chirperMap.put(chirp.getName(),chirp.getChirper());
            }else if(chirp.getMethod() == ChirpMethod.STOP){
                this.chirperMap.remove(chirp.getName());
            }else if(chirp.getMethod() == ChirpMethod.DISCOVER){
                if(chirp.getName().trim().compareTo("") == 0 || chirp.getName().trim().compareTo(this.chirper.getName()) == 0 ){
                    this.chirpBroadcaster.publish(this.chirper);
                }
            }else{
                logger.warn("This bird doesn't know how to chirp ---- {}", chirp.toString());
            }

        }
    }
}
