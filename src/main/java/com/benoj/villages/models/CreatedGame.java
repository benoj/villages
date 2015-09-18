package com.benoj.villages.models;


import org.mongojack.Id;

public class CreatedGame {


    @Id
    private String id;

    public String getId() {
        return id;
    }
}
