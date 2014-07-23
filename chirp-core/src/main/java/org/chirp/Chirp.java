package org.chirp;


import com.google.gson.Gson;
import org.chirp.config.ChirpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 16/07/14
 * Time: 2:34 PM
 *
 */

/**
 * This class represents the message that chirpers (Services using chirp) use
 * for communication
 *
 */
public class Chirp {

    /**
     * method used for communication, values are {@link org.chirp.config.ChirpMethod}
     *
     */
    private ChirpMethod method;

    /**
     * name of the service for which the chirp is intended
     */
    private String name;

    /**
     * uri of the service sending the chirp
     */
    private String uri;

    /**
     * port of the service sending the chirp
     */
    private int port;

    /**
     * protocol used by the service sending the chirp
     */
    private String protocol;

    /**
     * config for sending custom information for the service
     */
    private Map<String, Object> config;

    /**
     * name of the service sending the chirp
     */
    private String sender;


    public ChirpMethod getMethod() {
        return method;
    }

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

    public String getSender() {
        return sender;
    }

    public Chirp(ChirpMethod method,
                 String name,
                 String uri,
                 int port,
                 String protocol,
                 Map<String, Object> config,
                 String sender) {
        this.method = method;
        this.name = name;
        this.uri = uri;
        this.port = port;
        this.protocol = protocol;
        this.config = config;
        this.sender = sender;
    }

    public static class ChirpBuilder{
        private ChirpMethod method;
        private String name;
        private String uri;
        private int port;
        private String protocol;
        private Map<String, Object> config;
        private String sender;


        public ChirpBuilder(){

        }

        public ChirpBuilder(String method,String name){

        }

        public ChirpBuilder(ChirpMethod method, Chirper chirper){
            this.method = method;
            this.name = chirper.getName();
            this.uri = chirper.getUri();
            this.port = chirper.getPort();
            this.protocol= chirper.getProtocol();
            this.config = chirper.getConfig();

        }

        public ChirpBuilder setMethod(ChirpMethod method) {
            this.method = method;
            return this;
        }

        public ChirpBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ChirpBuilder setUri(String uri) {
            this.uri = uri;
            return this;
        }

        public ChirpBuilder setPort(int port) {
            this.port = port;
            return this;
        }

        public ChirpBuilder setProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public ChirpBuilder setConfig(Map<String, Object> config) {
            this.config = config;
            return this;
        }

        public ChirpBuilder setSender(String sender) {
            this.sender = sender;
            return this;
        }


        public Chirp build(){

            if(null == name){
                throw new IllegalArgumentException("name cannot be null");
            }
            if(null == uri){
                this.uri = "";
            }
            if(null == protocol){
                this.protocol = "";
            }
            if(null == method){
                throw new IllegalArgumentException("method cannot be null");
            }
            if(null == sender){
                this.sender = name;
            }
            if(null == config){
                this.config = new HashMap<String, Object>();
            }
            return new Chirp(method,name,uri,port,protocol,config,sender);
        }


    }

    public Chirper getChirper(){
        return new Chirper(this.getName(),this.getUri(),this.getPort(),this.getProtocol(),this.getConfig());
    }

    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
