package com.frcscout.teammember;

import java.sql.Connection;

import com.frcscout.sql.DBConnection;
import com.frcscout.sql.MySQLConnection;

public class GroupByEventBean {
    private Integer selectedEvent;
    private Connection conn;
    private DBConnection dbconn;
    
    public GroupByEventBean() {
        dbconn = new MySQLConnection();
        conn = null;
        selectedEvent = null;
    }
    
    public String getOverviewTable() {
        //TODO: return json
        return null;
    }
    
    public String getOverviewChart() {
        //TODO: return json
        return null;
    }
    
    public String getRedTeamTable() {
        //TODO: return json
        return null;
    }
    
    public String getBlueTeamTable() {
        //TODO: return json
        return null;
    }
    
    public int getMatchSearchResult(String search){
        //TODO: return int if record found, return -1 else
        return -1;
    }
    
    public String getTeamTable() {
        //TODO: return json
        return null;
    }
    
    public String getTeamLineGraph() {
        //TODO: return json
        return null;
    }
    
    public String getTeamPieChart() {
        //TODO: return json
        return null;
    }
    
    public int getSelectedEvent() {
        return this.selectedEvent.intValue();
    }
    
    public void setSelectedEvent(int event) {
        this.selectedEvent = event;
    }

}
