package com.benoj.villages;

import com.benoj.villages.model.Game;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mongojack.JacksonDBCollection;

public class ApplicationTest {

    @ClassRule
    public static MongoRule rule = new MongoRule();


    private JacksonDBCollection<Game,String> games;


    @Before
    public void before(){
        this.games = rule.getEmptyCollectionOf("games", Game.class);
    }

    @Test
    public void testName() throws Exception {
    }

}
