package com.frcscout.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.frcscout.sql.DBConnection;
import com.frcscout.sql.MySQLConnection;
import com.frcscout.util.StringUtil;

public class EventBean {
    private Integer id;
    private String name;
    private String location;
    private String startDate;
    private String endDate;
    private Connection conn;
    private DBConnection dbconn;

    
    public EventBean() {
        this.dbconn = new MySQLConnection();
        this.id = null;
        this.name = null;
        this.location = null;
        this.startDate = null;
        this.endDate = null;
        this.conn = null;
    }
    
    public EventBean(DBConnection dbconn) {
        this.dbconn = dbconn;
        this.id = null;
        this.name = null;
        this.location = null;
        this.startDate = null;
        this.endDate = null;
        this.conn = null;
    }
    
    public void loadEvent(String id) {
        if (!StringUtil.isBlank(id)) {
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                conn = dbconn.getConnection();
                st = conn.prepareStatement("SELECT * FROM events WHERE id = " + id + " limit 1");
                rs = st.executeQuery();
                if (rs.next()) {
                    setId(rs.getInt("id"));
                    setName(rs.getString("name"));
                    setLocation(rs.getString("location"));
                    if (rs.getDate("start_date") != null) {
                        this.startDate = rs.getDate("start_date").toString();
                    }
                    if (rs.getDate("end_date") != null) {
                        this.endDate = rs.getDate("end_date").toString();
                    }
                } else {
                    System.out.println("event id " + this.id.toString() + " not found");
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
        }
    }
    
    @SuppressWarnings("unchecked")
    public String loadEvents() {
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT * FROM events");
            rs = st.executeQuery();
            while (rs.next()) {
                JSONObject o = new JSONObject();
                o.put("id", rs.getInt("id"));
                o.put("name", rs.getString("name"));
                o.put("location", rs.getString("location"));
                if (rs.getDate("start_date") != null) {
                    o.put("start_date", rs.getDate("start_date").toString());
                } else {
                    o.put("start_date", "");
                }
                if (rs.getDate("end_date") != null){
                    o.put("end_date", rs.getDate("end_date").toString());
                } else {
                    o.put("end_date", "");
                }
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
    
    public void updateEvent() {
        if (id != null) {
            PreparedStatement st = null;
            String q = "UPDATE events SET name = ?, location = ?, start_date = ?, end_date = ? WHERE id = ?";

            try {
                java.sql.Date sd = null;
                java.sql.Date ed = null;
                if (this.startDate != null) {
                    sd = new java.sql.Date(StringUtil.stringToDate(startDate).getTime());
                }
                if (this.endDate != null) {
                    ed = new java.sql.Date(StringUtil.stringToDate(endDate).getTime());
                }
                conn = dbconn.getConnection();
                st = conn.prepareStatement(q);
                st.setString(1, this.name);
                st.setString(2, this.location);
                st.setDate(3, sd);
                st.setDate(4, ed);
                st.setInt(5, this.id.intValue());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    st.close();
                } catch (SQLException e) {
                    System.out.println("Unable to close connection");
                }
            }
        } else {
            System.out.println("Unable to update event because id is null");
        }
    }
    
    public void insertEvent() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String q = "INSERT INTO events SET name = ?, location = ?, start_date = ?, end_date = ?";
        try {
            java.sql.Date sd = null;
            java.sql.Date ed = null;
            if (this.startDate != null) {
                sd = new java.sql.Date(StringUtil.stringToDate(startDate).getTime());
            }
            if (this.endDate != null) {
                ed = new java.sql.Date(StringUtil.stringToDate(endDate).getTime());
            }
            conn = dbconn.getConnection();
            st = conn.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, this.name);
            st.setString(2, this.location);
            st.setDate(3, sd);
            st.setDate(4, ed);
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Unable to close connection");
            }
        }
    }
    
    public void deleteEvent() {
        if (id != null) {
            PreparedStatement st = null;
            String q = "DELETE FROM events WHERE id = ?";

            try {
                conn = dbconn.getConnection();
                st = conn.prepareStatement(q);
                st.setInt(1, this.id.intValue());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    st.close();
                } catch (SQLException e) {
                    System.out.println("Unable to close connection");
                }
            }
        } else {
            System.out.println("Unable to delete event because id is null");
        }
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
            this.location = null;
        }
    }
    
    public void setStartDate(String startDate) {
        if (!StringUtil.isBlank(startDate)) {
            this.startDate = startDate;
        } else {
            this.startDate = null;
        }
    }
    
    public void setEndDate(String endDate) {
        if (!StringUtil.isBlank(endDate)) {
            this.endDate = endDate;
        } else {
            this.endDate = null;
        }
    }
    
    public int getId() {
        if (id != null) {
            return this.id.intValue();
        } else {
            return -1;
        }
        
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getLocation(){
        return this.location;
    }
    
    public String getStartDate() {
        return this.startDate;
    }
    
    public String getEndDate(){
        return this.endDate;
    }
}
