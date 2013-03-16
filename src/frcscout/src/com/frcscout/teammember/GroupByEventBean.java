package com.frcscout.teammember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.frcscout.sql.DBConnection;
import com.frcscout.sql.MySQLConnection;

public class GroupByEventBean {
    private Integer selectedEvent;
    private Integer selectedMatch;
    private Integer selectedTeam;
    private Connection conn;
    private DBConnection dbconn;
    
    public GroupByEventBean() {
        this.dbconn = new MySQLConnection();
        this.conn = null;
        this.selectedEvent = getDefaultEvent();
        this.selectedMatch = null;
        this.selectedTeam = null;
    }
    
    private int getDefaultEvent() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT * FROM events order by start_date desc limit 1");
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
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
    public String getOverviewTable() {
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT team_id, sum(auton_top)*6 + sum(auton_middle)*4  + sum(auton_bottom)*2 as auton, sum(teleop_top)*3 + sum(teleop_middle)*2 + sum(teleop_bottom) + sum(teleop_pyramid)*5 as teleop, sum(pyramid_level) as climb, sum(auton_top)*6 + sum(auton_middle)*4  + sum(auton_bottom)*2 + sum(teleop_top)*3 + sum(teleop_middle)*2 + sum(teleop_bottom) + sum(teleop_pyramid)*5 + sum(pyramid_level) as total FROM match_record_2013 WHERE event_id = ? GROUP BY team_id");
            st.setInt(1, getSelectedEvent());
            rs = st.executeQuery();
            while (rs.next()) {
                JSONObject o = new JSONObject();

                o.put("id", rs.getInt("team_id"));
                o.put("autonomous", rs.getInt("auton"));
                o.put("teleop", rs.getInt("teleop"));
                o.put("climb", rs.getInt("climb"));
                o.put("total_points", rs.getInt("total"));
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
            st = conn.prepareStatement("SELECT team_id, sum(auton_top)*6 + sum(auton_middle)*4  + sum(auton_bottom)*2 as auton, sum(teleop_top)*3 + sum(teleop_middle)*2 + sum(teleop_bottom) + sum(teleop_pyramid)*5 as teleop, sum(pyramid_level) as climb, sum(auton_top)*6 + sum(auton_middle)*4  + sum(auton_bottom)*2 + sum(teleop_top)*3 + sum(teleop_middle)*2 + sum(teleop_bottom) + sum(teleop_pyramid)*5 + sum(pyramid_level) as total FROM match_record_2013 WHERE event_id = ? GROUP BY team_id order by total desc limit 10");
            st.setInt(1, getSelectedEvent());
            rs = st.executeQuery();
            while (rs.next()) {
                JSONObject o = new JSONObject();
                
                o.put("id", rs.getInt("team_id"));
                o.put("autonomous", rs.getInt("auton"));
                o.put("teleop", rs.getInt("teleop"));
                o.put("climb", rs.getInt("climb"));
                o.put("total_points", rs.getInt("total"));
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
    public String getRedTeamTable() {
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT team_id, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_low, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 where event_id = ? AND match_number = ? AND color = 'red' GROUP BY team_id");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, getSelectedTeam());
            rs = st.executeQuery();
            while (rs.next()) {
                JSONObject o = new JSONObject();
                int autonTotal = 6 * rs.getInt("auton_top") + 4 * rs.getInt("auton_mid") + 2 * rs.getInt("auton_low");
                int teleopTotal = 3 * rs.getInt("teleop_top") + 2 * rs.getInt("teleop_mid") + 1 * rs.getInt("teleop_low") + 5 * rs.getInt("teleop_pyramid");
                int climbTotal = rs.getInt("climb");
                
                o.put("id", rs.getInt("team_id"));
                o.put("autonomous", autonTotal);
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
    public String getBlueTeamTable() {
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT team_id, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_low, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 where event_id = ? AND match_number = ? AND color = 'blue' GROUP BY team_id");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, getSelectedMatch());
            rs = st.executeQuery();
            while (rs.next()) {
                JSONObject o = new JSONObject();
                int autonTotal = 6 * rs.getInt("auton_top") + 4 * rs.getInt("auton_mid") + 2 * rs.getInt("auton_low");
                int teleopTotal = 3 * rs.getInt("teleop_top") + 2 * rs.getInt("teleop_mid") + 1 * rs.getInt("teleop_low") + 5 * rs.getInt("teleop_pyramid");
                int climbTotal = rs.getInt("climb");
                
                o.put("id", rs.getInt("team_id"));
                o.put("autonomous", autonTotal);
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
    
    public int getTeamSearchResult(String search){
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT * FROM match_record_2013 where event_id = ? AND team_id = ?");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, Integer.parseInt(search));
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("team_id");
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
    public String getTeamTable() {
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT match_number, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_low, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 WHERE event_id = ? AND team_id = ? GROUP BY match_number");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, getSelectedTeam());
            rs = st.executeQuery();
            while (rs.next()) {
                JSONObject o = new JSONObject();
                int autonTotal = 6 * rs.getInt("auton_top") + 4 * rs.getInt("auton_mid") + 2 * rs.getInt("auton_low");
                int teleopTotal = 3 * rs.getInt("teleop_top") + 2 * rs.getInt("teleop_mid") + 1 * rs.getInt("teleop_low") + 5 * rs.getInt("teleop_pyramid");
                int climbTotal = rs.getInt("climb");
                
                o.put("id", rs.getInt("match_number"));
                o.put("autonomous", autonTotal);
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
    public String getTeamLineGraph() {
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT match_number, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_low, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 WHERE event_id = ? AND team_id = ? GROUP BY match_number");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, getSelectedTeam());
            rs = st.executeQuery();
            while (rs.next()) {
                JSONObject o = new JSONObject();
                int autonTotal = 6 * rs.getInt("auton_top") + 4 * rs.getInt("auton_mid") + 2 * rs.getInt("auton_low");
                int teleopTotal = 3 * rs.getInt("teleop_top") + 2 * rs.getInt("teleop_mid") + 1 * rs.getInt("teleop_low") + 5 * rs.getInt("teleop_pyramid");
                int climbTotal = rs.getInt("climb");
                
                o.put("id", rs.getInt("match_number"));
                o.put("autonomous", autonTotal);
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
    public String getTeamPieChart() {
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT match_number, sum(auton_top) as auton_top, sum(auton_middle) as auton_mid, sum(auton_bottom) as auton_low, sum(teleop_top) as teleop_top, sum(teleop_middle) as teleop_mid, sum(teleop_bottom) as teleop_low, sum(teleop_pyramid) as teleop_pyramid, sum(pyramid_level) as climb FROM match_record_2013 WHERE event_id = ? AND team_id = ? GROUP BY match_number");
            st.setInt(1, getSelectedEvent());
            st.setInt(2, getSelectedTeam());
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
        if (this.selectedEvent != null) {
            return this.selectedEvent.intValue();
        } else {
            return -1;
        }
    }
    
    public void setSelectedEvent(int event) {
        if (event > 0) {
            this.selectedEvent = Integer.valueOf(event);
        }
    }
    
    public int getSelectedTeam() {
        if (this.selectedTeam != null) {
            return this.selectedTeam.intValue();
        } else {
            return -1;
        }
    }
    
    public void setSelectedTeam(int team) {
        this.selectedTeam = Integer.valueOf(team);
    }
    
    public int getSelectedMatch() {
        if (this.selectedMatch != null) {
            return this.selectedMatch.intValue();
        } else {
            return -1;
        }
    }
    
    public void setSelectedMatch(int match) {
        this.selectedMatch = Integer.valueOf(match);
    }

}
