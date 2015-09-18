package com.benoj.villages;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import io.dropwizard.testing.DropwizardTestSupport;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.rules.ExternalResource;
import org.mongojack.JacksonDBCollection;

import javax.ws.rs.client.WebTarget;
import java.net.UnknownHostException;

public class TestApplicationRule extends ExternalResource {


    private DB db;
    private final DropwizardTestSupport<VillagesConfiguration> application;

    public TestApplicationRule() {
        application = new DropwizardTestSupport<VillagesConfiguration>(
            VillagesApplication.class,
            this.getClass().getClassLoader().getResource("villages-test.yml").getFile()
        );
    }

    @Override
    public void before() {
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to connect to DB");
        }
        application.before();
        this.db = mongoClient.getDB("villages");
    }

    @Override
    public void after(){
        application.after();
    }


    public WebTarget getClient(){
        return JerseyClientBuilder.createClient().target(String.format("http://localhost:%s",application.getLocalPort()));
    }

    public <T> JacksonDBCollection<T, String> getMongoCollectionFor(String collectionName, Class<T> type) {
        DBCollection collection = db.getCollection(collectionName);
        collection.drop();
        return JacksonDBCollection.wrap(collection, type, String.class);
    }

}
