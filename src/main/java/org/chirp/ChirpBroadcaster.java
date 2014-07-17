package org.chirp;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ChirpBroadcaster{

    void publish(Chirper chirper);

    void shutdown(Chirper chirper);

    void discover(Chirper chirper);

    void discover(Chirper chirper, String name);

}
