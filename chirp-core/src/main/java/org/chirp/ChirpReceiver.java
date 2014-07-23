package org.chirp;


/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 16/07/14
 * Time: 1:17 PM
 *
 */

/**
 * Implemented by class that receives {@link Chirp} from other {@link Chirper}s on the network
 *
 */
public interface ChirpReceiver extends Runnable {

    /**
     * Used for registering {@link ChirpObserver}s, which would be notified whenever a
     * {@link Chirp} is received
     *
     * @param chirpObserver {@link ChirpObserver} that needs to be registered
     */
    void registerChirpObserver(ChirpObserver chirpObserver);

    /**
     * Used for receiving message from other {@link Chirper}s and
     * returning a {@link Chirp } after de-serializing the {@code string} message
     *
     * @param message
     * @return {@link Chirp} Chirp sent by other chirpers on the networl
     */
    Chirp receive(String message);

    /**
     * Used for stopping the Chirp Receiver
     *
     */
    void stop();

}
