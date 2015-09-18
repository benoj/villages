package com.benoj.villages;


import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class VillagesApplication extends Application<VillagesConfiguration>{

    public static void main(String... args) throws Exception {
        new VillagesApplication().run(args);
    }

    @Override
    public void run(final VillagesConfiguration configuration, final Environment environment) throws Exception {

    }
}
