package com.frcscout.admin;

import java.sql.Connection;

import com.frcscout.sql.DBConnection;
import com.frcscout.sql.MySQLConnection;

public class UserAccountBean {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Connection conn;
    private DBConnection dbconn;
    
    public UserAccountBean() {
        dbconn = new MySQLConnection();
        email = null;
        password = null;
        firstName = null;
        lastName = null;
    }
    
    public void loadUser(String email) { //select 1 users by email
        //TODO
    }
    
    public String loadUsers() { // select all users, return JSON string
        //TODO
        return null;
    }
    
    public void updateUser() { // update entry in database
        //TODO
    }
    
    public void insertUser(){ //create entry in database
        //TODO
    }
    
    public void deleteUser() { //delete entry from database
        //TODO
    }
    
    public void setEmail(String email) {
        //TODO
    }
    
    public void setPassword (String password) {
      //TODO
    }
    
    public void setFirstName(String firstName) {
      //TODO
    }
    
    public void setLastName(String lastName) {
        //TODO
    }
    
    public String getEmail() {
        //TODO
        return null;
    }
    
    public String getPassword () {
      //TODO
        return null;
    }
    
    public String getFirstName() {
      //TODO
        return null;
    }
    
    public String getLastName() {
        //TODO
        return null;
    }
}
