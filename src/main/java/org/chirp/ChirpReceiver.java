package org.chirp;


/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ChirpReceiver extends Runnable {

    void registerChirpObserver(ChirpObserver chirpObserver);

    Chirp receive(String message);

    void stop();

}
