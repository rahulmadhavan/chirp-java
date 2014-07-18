package org.chirp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

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

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getUri() {
        return uri;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty
    public String getProtocol() {
        return protocol;
    }

    @JsonProperty
    public Map<String, Object> getConfig() {
        return config;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }

    @JsonProperty
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @JsonProperty
    public void setConfig(Map<String, Object> config) {
        this.config = config;
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

    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }



}
