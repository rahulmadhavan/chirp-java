package org.chirp;


/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 16/07/14
 * Time: 6:56 PM
 *
 */

/**
 * Implemented by a Class that wants to observe the {@link Chirp}s
 * received by the {@link ChirpReceiver}
 *
 */
public interface ChirpObserver {

    /**
     * method called by the {@link ChirpReceiver} when it receives a {@link Chirp}
     *
     * @param chirp {@link Chirp} received by the {@link ChirpReceiver}
     */
    void notify(Chirp chirp);

}
