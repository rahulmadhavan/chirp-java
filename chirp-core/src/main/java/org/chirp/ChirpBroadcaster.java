package org.chirp;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 16/07/14
 * Time: 1:18 PM
 *
 */

/**
 * To Be implemented by a Class that wants to
 * broadcast Chirps to other Chirpers (Services using Chirp)
 *
 */
public interface ChirpBroadcaster{

    /**
     * Used for publishing a {@code chirper} to other chirpers
     *
     * @param chirper {@link Chirper} that wants to publish itself
     */
    void publish(Chirper chirper);

    /**
     * Used by a {@code chirper} for telling other Chirpers that it is shutting down
     *
     * @param chirper {@link Chirper} that wants to shutdown
     */
    void shutdown(Chirper chirper);

    /**
     * Used by a {@code chirper} for discovering other chirpers on the network
     *
     * @param chirper {@link Chirper} that wants to discover other services
     */
    void discover(Chirper chirper);

    /**
     * Used by a {@code chirper} for discovering a service having the given {@code name}
     *
     * @param chirper {@link Chirper} that wants to discover other services
     * @param name of the chirper that needs to be discovered
     */
    void discover(Chirper chirper, String name);

}
