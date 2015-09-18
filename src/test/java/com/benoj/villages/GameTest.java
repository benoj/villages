package com.benoj.villages;

import com.benoj.villages.models.CreatedGame;
import com.benoj.villages.models.Game;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mongojack.JacksonDBCollection;

import javax.ws.rs.client.WebTarget;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameTest {

    @ClassRule
    public static TestApplicationRule application = new TestApplicationRule();
    private JacksonDBCollection<Game,String> gamesCollection;
    WebTarget client;


    @Before
    public void before(){
        this.gamesCollection = application.getMongoCollectionFor("games", Game.class);
        this.client = application.getClient();
    }

    @Test
    public void anEmptyPostShouldCreateNewGame() throws Exception {
        CreatedGame createdGame = client.path("game").request().post(null, CreatedGame.class);
        assertThat(gamesCollection.count(), is(1l));
        Game dbGame = gamesCollection.findOne();
        assertThat(dbGame.getId(), not(nullValue()));
        assertThat(createdGame.getId(), is(dbGame.getId()));
    }



}
