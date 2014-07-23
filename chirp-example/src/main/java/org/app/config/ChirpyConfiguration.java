package org.app.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.chirp.Chirper;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 18/07/14
 * Time: 3:12 PM
 *
 */


public class ChirpyConfiguration extends Configuration {
    @NotEmpty
    private String uri;

    @NotEmpty
    private String name;

    @NotNull
    private Integer port;

    @NotEmpty
    private String protocol;

    @JsonProperty
    public String getUri() {
        return uri;
    }

    @JsonProperty
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }

    @JsonProperty
    public String getProtocol() {
        return protocol;
    }

    @JsonProperty
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Chirper getChirper(){
        return new Chirper(name,uri,port,protocol);
    }

}
