package com.benoj.villages;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.junit.rules.ExternalResource;
import org.mongojack.JacksonDBCollection;

import java.net.UnknownHostException;

public class MongoRule extends ExternalResource {

    private DB db;

    @Override
    public void before(){
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to connect to DB");
        }
       this.db = mongoClient.getDB("villages");
    }


    public  <T> JacksonDBCollection<T,String> getEmptyCollectionOf(String collectionName, Class<T> type){
        DBCollection collection = db.getCollection(collectionName);
        collection.drop();
        return JacksonDBCollection.wrap(collection, type, String.class);
    }

}
