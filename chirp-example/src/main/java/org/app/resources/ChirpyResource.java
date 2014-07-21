package org.app.resources;


import org.chirp.ChirpManager;
import org.chirp.Chirper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 18/07/14
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ChirpyResource {

    private ChirpManager chirpManager;



    public ChirpyResource(ChirpManager chirpManager){
        this.chirpManager = chirpManager;
    }

    @GET

    public String hello(){
        return "Hello";
    }

    @GET
    @Path("chirpers")
    public Map<String,Chirper> getChirpers(){
        return chirpManager.listChirpers();
    }

    @GET
    @Path("chirper")
    public Chirper getChirper(){
        return chirpManager.getChirper();
    }

    @GET
    @Path("chirper/{name}")
    public Response getChirper(@PathParam("name") String name){
        Chirper chirper = chirpManager.fetchChirper(name);
        if(null == chirper){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(chirper).build();
        }
    }

}
