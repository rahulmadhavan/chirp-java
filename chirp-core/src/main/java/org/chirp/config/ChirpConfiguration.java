package org.chirp.config;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 23/07/14
 * Time: 4:37 PM
 *
 */

/**
 * Default values for certain fields
 *
 */
public class ChirpConfiguration {

    private static int multiCastPort = 9292;
    private static String multiCastAddress = "224.1.1.4";

    public static int getMultiCastPort() {
        return multiCastPort;
    }

    public static String getMultiCastAddress() {
        return multiCastAddress;
    }
}
