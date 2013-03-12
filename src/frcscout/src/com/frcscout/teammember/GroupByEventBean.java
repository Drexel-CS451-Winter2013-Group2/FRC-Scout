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
            st = conn.prepareStatement("SELECT team_id, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_top, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 WHERE event_id = ? GROUP BY team_id");
            st.setInt(1, getSelectedEvent());
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
            	json.add(o);
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
    
    @SuppressWarnings("unchecked")
    public String getOverviewChart() {
    	PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT team_id, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_top, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 WHERE event_id = ? GROUP BY team_id");
            st.setInt(1, getSelectedEvent());
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
            	json.add(o);
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
    
    @SuppressWarnings("unchecked")
    public String getRedTeamTable(int matchId) {
    	PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT team_id, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_top, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 where event_id = ? AND match_number = ? AND color = 'red' GROUP BY team_id");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, matchId);
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
            	json.add(o);
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
    
    @SuppressWarnings("unchecked")
    public String getBlueTeamTable(int matchId) {
    	PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT team_id, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_top, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 where event_id = ? AND match_number = ? AND color = 'blue' GROUP BY team_id");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, matchId);
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
            	json.add(o);
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
    
    public int getMatchSearchResult(String search){
    	PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT * FROM match_record_2013 where event_id = ? AND match_number = ?");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, Integer.parseInt(search));
            rs = st.executeQuery();
            if (rs.next()) {
            	return rs.getInt("match_number");
            } else {
            	return -1;
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
        return -1;
    }
    
    @SuppressWarnings("unchecked")
    public String getTeamTable(int teamId) {
    	PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT match_number, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_top, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 WHERE event_id = ? AND team_id = ?");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, teamId);
            rs = st.executeQuery();
            while (rs.next()) {
            	JSONObject o = new JSONObject();
            	int autonTotal = 6 * rs.getInt("auton_top") + 4 * rs.getInt("auton_mid") + 2 * rs.getInt("auton_low");
            	int teleopTotal = 3 * rs.getInt("teleop_top") + 2 * rs.getInt("teleop_mid") + 1 * rs.getInt("teleop_low") + 5 * rs.getInt("teleop_pyramid");
            	int climbTotal = rs.getInt("climb");
            	
            	o.put("id", rs.getInt("match_number"));
            	o.put("autononmous", autonTotal);
            	o.put("teleop", teleopTotal);
            	o.put("climb", climbTotal);
            	o.put("total_points", autonTotal + teleopTotal + climbTotal);
            	json.add(o);
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
    
    @SuppressWarnings("unchecked")
    public String getTeamLineGraph(int teamId) {
    	PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT match_number, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_top, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 WHERE event_id = ? AND team_id = ?");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, teamId);
            rs = st.executeQuery();
            while (rs.next()) {
            	JSONObject o = new JSONObject();
            	int autonTotal = 6 * rs.getInt("auton_top") + 4 * rs.getInt("auton_mid") + 2 * rs.getInt("auton_low");
            	int teleopTotal = 3 * rs.getInt("teleop_top") + 2 * rs.getInt("teleop_mid") + 1 * rs.getInt("teleop_low") + 5 * rs.getInt("teleop_pyramid");
            	int climbTotal = rs.getInt("climb");
            	
            	o.put("id", rs.getInt("match_number"));
            	o.put("autononmous", autonTotal);
            	o.put("teleop", teleopTotal);
            	o.put("climb", climbTotal);
            	o.put("total_points", autonTotal + teleopTotal + climbTotal);
            	json.add(o);
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
    
    @SuppressWarnings("unchecked")
    public String getTeamPieChart(int teamId) {
    	PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT match_number, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_top, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 WHERE event_id = ? AND team_id = ?");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, teamId);
            rs = st.executeQuery();
            int autonTotal = 0;
            int teleopTotal = 0;
            int climbTotal = 0;
            while (rs.next()) {
            	autonTotal += 6 * rs.getInt("auton_top") + 4 * rs.getInt("auton_mid") + 2 * rs.getInt("auton_low");
            	teleopTotal += 3 * rs.getInt("teleop_top") + 2 * rs.getInt("teleop_mid") + 1 * rs.getInt("teleop_low") + 5 * rs.getInt("teleop_pyramid");
            	climbTotal += rs.getInt("climb");
            }
            JSONObject o1 = new JSONObject();
        	o1.put("category", "autonomous");
        	o1.put("total", autonTotal);
        	json.add(o1);
        	JSONObject o2 = new JSONObject();
         	o2.put("category", "teleop");
         	o2.put("total", teleopTotal);
         	json.add(o2);
        	JSONObject o3 = new JSONObject();
         	o3.put("category", "clilmb");
         	o3.put("total", climbTotal);
         	json.add(o3);
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
    
    public int getSelectedEvent() {
        return this.selectedEvent.intValue();
    }
    
    public void setSelectedEvent(int event) {
        this.selectedEvent = event;
    }

}
