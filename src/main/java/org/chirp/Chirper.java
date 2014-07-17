package org.chirp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Chirper {
    private String name;
    private String uri;
    private int port;
    private String protocol;
    private Map<String, Object> config;

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public Chirper(String name, String uri, int port, String protocol, Map<String, Object> config) {
        this.name = name;
        this.uri = uri;
        this.port = port;
        this.protocol = protocol;
        this.config = config;
    }

    public Chirper(String name, String uri, int port, String protocol){
        this.name = name;
        this.uri = uri;
        this.port = port;
        this.protocol = protocol;
        this.config = new HashMap<String, Object>();
    }



}
