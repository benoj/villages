package com.benoj.villages.models;

import org.mongojack.Id;

public class Game {

    @Id
    private String id;


    public String getId() {
        return id;
    }
}
