package org.chirp;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ChirpManager extends ChirpObserver {

    void startChirping();

    void stop();

    Set<String> listChirpers();

    Chirper fetchChirper(String name);


}
