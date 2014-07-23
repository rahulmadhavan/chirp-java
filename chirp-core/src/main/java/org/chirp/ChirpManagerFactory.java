package org.chirp;

import org.chirp.config.ChirpConfiguration;
import org.chirp.impl.ChirpBroadcasterImpl;
import org.chirp.impl.ChirpManagerImpl;
import org.chirp.impl.ChirpReceiverImpl;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 18/07/14
 * Time: 4:19 PM
 *
 */

/**
 * Used for creating a Chirp Manager
 */
public class ChirpManagerFactory {


    private Chirper chirper;
    private String multiCastAddress;
    private int multiCastPort;

    /**
     *
     * @param chirper {@link Chirper} for which Chirp Manager needs to be created
     */
    public ChirpManagerFactory(Chirper chirper){
        this.chirper = chirper;
        this.multiCastAddress = ChirpConfiguration.getMultiCastAddress();
        this.multiCastPort = ChirpConfiguration.getMultiCastPort();
    }

    /**
     *
     * @param chirper {@link Chirper} for which Chirp Manager needs to be created
     * @param multiCastAddress multiCast address at which all Chirpers will broadcast and publish
     * @param multiCastPort multiCast port at which all Chirpers will broadcast and publish
     */
    public ChirpManagerFactory(Chirper chirper, String multiCastAddress, int multiCastPort){
        this.multiCastAddress = multiCastAddress;
        this.multiCastPort = multiCastPort;
        this.chirper = chirper;
    }

    /**
     *  creates a {@link ChirpManager} along with {@link ChirpReceiver} and {@link ChirpBroadcaster}
     *
     * @return {@link ChirpManager}
     */
    public ChirpManager createChirpManager(){
        ChirpReceiver chirpReceiver = new ChirpReceiverImpl(this.multiCastAddress,this.multiCastPort);
        ChirpBroadcaster chirpBroadcaster = new ChirpBroadcasterImpl(this.multiCastAddress,this.multiCastPort);
        return new ChirpManagerImpl(chirper,chirpBroadcaster,chirpReceiver);
    }

}
