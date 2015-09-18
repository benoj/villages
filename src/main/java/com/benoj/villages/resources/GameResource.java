package com.benoj.villages.resources;

import com.benoj.villages.models.Game;
import com.google.common.collect.ImmutableMap;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/game")
public class GameResource {


    private final JacksonDBCollection<Game, String> games;

    public GameResource(final JacksonDBCollection<Game, String> games) {

        this.games = games;
    }

    @POST
    @Produces("application/json")
    public Response newGame(){
        WriteResult<Game, String> insert = games.insert(new Game());
        return Response.ok(ImmutableMap.of("id",insert.getSavedId())).build();
    }
}
