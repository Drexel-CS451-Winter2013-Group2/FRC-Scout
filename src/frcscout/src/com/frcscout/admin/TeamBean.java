package com.frcscout.admin;

import java.sql.Connection;

import com.frcscout.sql.DBConnection;
import com.frcscout.sql.MySQLConnection;
import com.frcscout.util.StringUtil;

public class TeamBean {
    private Integer id;
    private String name;
    private String location;
    private Connection conn;
    private DBConnection dbconn;
    
    public TeamBean() {
        dbconn = new MySQLConnection();
        id = null;
        name = null;
        location = null;
    }
    
    public void loadTeam(int id) { //select 1 team by id
        //TODO
    }
    
    public String loadTeams() { //select all teams, return JSON formatted string
        //TODO
        return null;
    }
    
    public void updateTeam() { //update entry in database for id
        //TODO
    }
    
    public void insertTeam(){ //create entry in database
        //TODO
    }
    
    public void deleteTeam() {
        //TODO
    }
    
    public void setId(int id) {
        this.id = Integer.valueOf(id);
    }
    
    public void setName(String name){
        if (!StringUtil.isBlank(name)) {
            this.name = name.trim();
        } else {
            this.name = null;
        }
    }
    
    public void setLocation(String location){
        if (!StringUtil.isBlank(location)) {
            this.location = location;
        } else {
            this.name = null;
        }
    }
    
    public int getId() {
        return this.id.intValue();
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getLocation(){
        return this.location;
    }
}
