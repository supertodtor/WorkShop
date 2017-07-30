package com.mycom.coe.persistent.model;

/**
 * Created by coe on 7/30/2017.
 */

public class Board {
    public static final String TABLE_NAME = "Board";
    public static final String COL_TITLE = "title";
    public static final String COL_NAME = "name";
    public static final String COL_MESSAGE = "message";

    private int id;
    private String title;
    private String name;
    private String message;

    public Board() {

    }

    public Board(  String title, String name, String message) {
        this.title = title;
        this.name = name;
        this.message = message;
    }

    public Board(int id, String title, String name, String message) {
        this(title,name,message);
        this.id = id;

    }


    @Override
    public String toString() {
        return "Title: " + title + "Name: " + name + "Message: " + message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
