package org.app;


import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.app.config.ChirpyConfiguration;
import org.app.resources.ChirpyResource;
import org.chirp.ChirpManager;
import org.chirp.ChirpManagerFactory;



/**
 * Created with IntelliJ IDEA.
 * User: rahulmadhavan
 * Date: 18/07/14
 * Time: 3:16 PM
 *
 */



public class ChirpyApplication extends Service<ChirpyConfiguration> {


    private ChirpManager chirpManager;

    public static void main(String[] args) throws Exception {
        new ChirpyApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ChirpyConfiguration> bootstrap) {

    }

    @Override
    public void run(ChirpyConfiguration configuration,
                    Environment environment) {
        chirpManager = new ChirpManagerFactory(configuration.getChirper()).createChirpManager();
        chirpManager.startChirping();
        final ChirpyResource chirpyResource = new ChirpyResource(chirpManager);
        environment.addResource(chirpyResource);

    }

}

