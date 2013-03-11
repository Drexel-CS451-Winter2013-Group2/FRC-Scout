package com.frcscout.teammember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    
    @SuppressWarnings("unchecked")
    public String getOverviewTable() {
    	PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT team_id, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_top, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 where event_id = ? GROUP BY team_id");
            st.setString(1, "" + getSelectedEvent());
            rs = st.executeQuery();
            while (rs.next()) {
            	JSONObject o = new JSONObject();
            	int autonTotal = 6 * rs.getInt("auton_top") + 4 * rs.getInt("auton_mid") + 2 * rs.getInt("auton_low");
            	int teleopTotal = 3 * rs.getInt("teleop_top") + 2 * rs.getInt("teleop_mid") + 1 * rs.getInt("teleop_low") + 5 * rs.getInt("teleop_pyramid");
            	int climbTotal = rs.getInt("climb");
            	
            	o.put("id", rs.getInt("team_id"));
            	o.put("autononmous", autonTotal);
            	o.put("teleop", teleopTotal);
            	o.put("climb", climbTotal);
            	o.put("total_points", autonTotal + teleopTotal + climbTotal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                conn.close();
                st.close();
                rs.close();
            }catch (SQLException e) {
                System.out.println("Error closing query");
            }
        }
        return json.toString();
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
