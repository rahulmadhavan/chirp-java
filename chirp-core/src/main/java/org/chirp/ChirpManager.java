package org.chirp;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 16/07/14
 * Time: 1:17 PM
 *
 */

/**
 * Should Implemented by a class for discovering, publishing services using Chirp.
 * Also, provides lifecycle management methods for the {@link ChirpBroadcaster} and {@link ChirpReceiver}
 *
 */
public interface ChirpManager extends ChirpObserver {

    /**
     * Used for starting the Chirp Broadcaster and Chirp Receiver
     */
    void startChirping();

    /**
     * Used for Stopping the Chirp Broadcaster and Chirp Receiver
     */
    void stop();

    /**
     * returns a Map of Chirper name and Chirpers discovered
     *
     * @return Map of Chirper Name and Corresponding {@link Chirper}
     */
    Map<String,Chirper> listChirpers();

    /**
     * Used for discovering Chirper for the given {@code name}
     *
     * @param name of the chirper to be found
     * @return {@link Chirper} for the given name
     */
    Chirper fetchChirper(String name);

    /**
     * To get the details of the current Chirper
     *
     * @return {@link Chirper}
     */
    Chirper getChirper();

}
