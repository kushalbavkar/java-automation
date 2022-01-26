package com.java.automation.db.model;

public class Users {
    private int id;
    private String firstname;
    private String lastname;
    private String userid;

    public Users(int id, String firstname, String lastname, String userid) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
