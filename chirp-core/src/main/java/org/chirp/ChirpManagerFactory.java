package org.chirp;

import org.chirp.impl.ChirpBroadcasterImpl;
import org.chirp.impl.ChirpManagerImpl;
import org.chirp.impl.ChirpReceiverImpl;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 18/07/14
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChirpManagerFactory {


    private Chirper chirper;

    public ChirpManagerFactory(Chirper chirper){
        this.chirper = chirper;
    }

    public ChirpManager createChirpManager(){
        ChirpReceiver chirpReceiver = new ChirpReceiverImpl();
        ChirpBroadcaster chirpBroadcaster = new ChirpBroadcasterImpl();
        return new ChirpManagerImpl(chirper,chirpBroadcaster,chirpReceiver);
    }

}
