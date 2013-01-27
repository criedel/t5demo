package com.cupsoftware.carsharing.model;

public class User extends BaseEntity {

    private static final long serialVersionUID = -4740866844368343481L;

    private String name;

    private String passhash;

    public User() {

    }

    public User(String name,
                String passhash) {

        super();
        this.name = name;
        this.passhash = passhash;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPasshash() {

        return passhash;
    }

    public void setPasshash(String passhash) {

        this.passhash = passhash;
    }

}
