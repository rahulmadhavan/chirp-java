package org.chirp.config;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 23/07/14
 * Time: 4:41 PM
 *
 */


/**
 * Represents the different methods used by Chirp For Communication
 *
 * PUBLISH  : used for publishing a service
 * STOP     : used to indicate that a service, has stopped
 * DISCOVER : used for discovering services using chirp
 *
 */
public enum ChirpMethod {
    PUBLISH,
    DISCOVER,
    STOP
}
