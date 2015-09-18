package com.benoj.villages;


import com.benoj.villages.models.Game;
import com.benoj.villages.resources.GameResource;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.mongojack.JacksonDBCollection;

public class VillagesApplication extends Application<VillagesConfiguration>{

    public static void main(String... args) throws Exception {
        new VillagesApplication().run(args);
    }

    @Override
    public void run(final VillagesConfiguration configuration, final Environment environment) throws Exception {
        DB db = new MongoClient("localhost", 27017).getDB("villages");
        DBCollection collection = db.getCollection("games");
        JacksonDBCollection<Game, String> games = JacksonDBCollection.wrap(collection, Game.class, String.class);


        GameResource gamesResource = new GameResource(games);
        environment.jersey().register(gamesResource);
    }
}
