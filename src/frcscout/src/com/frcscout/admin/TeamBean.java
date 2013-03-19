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

public class TeamBean {
    private Integer id;
    private String name;
    private String location;
    private Connection conn;
    private DBConnection dbconn;
    
    public TeamBean() {
        this.dbconn = new MySQLConnection();
        this.id = null;
        this.name = null;
        this.location = null;
    }
    
    public TeamBean(DBConnection dbconn) {
        this.dbconn = dbconn;
        this.id = null;
        this.name = null;
        this.location = null;
    }
    
    public void loadTeam(String id) {
        if (!StringUtil.isBlank(id)) {
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                conn = dbconn.getConnection();
                st = conn.prepareStatement("SELECT * FROM team WHERE id = " + id + " limit 1");
                rs = st.executeQuery();
                if (rs.next()) {
                    setId(rs.getInt("id"));
                    setName(rs.getString("name"));
                    setLocation(rs.getString("location"));
                } else {
                    System.out.println("team number " + id.toString() + " not found");
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
    public String loadTeams() {
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONArray json = new JSONArray();
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement("SELECT * FROM team");
            rs = st.executeQuery();
            while (rs.next()) {
                JSONObject o = new JSONObject();
                o.put("id", rs.getInt("id"));
                o.put("name", rs.getString("name"));
                o.put("location", rs.getString("location"));
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
    
    public void updateTeam(String newId) {
        if (id != null) {
            PreparedStatement st = null;
            String q = "UPDATE team SET id = ?, name = ?, location = ? WHERE id = ?";

            try {
                conn = dbconn.getConnection();
                st = conn.prepareStatement(q);
                st.setInt(1, Integer.valueOf(newId));
                st.setString(2, this.name);
                st.setString(3, this.location);
                st.setInt(4, this.id.intValue());
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
            System.out.println("Unable to update team because id is null");
        }
    }
    
    public void insertTeam() {
        PreparedStatement st = null;
        String q = "INSERT INTO team SET id= ?, name = ?, location = ?";
        try {
            conn = dbconn.getConnection();
            st = conn.prepareStatement(q);
            st.setInt(1, this.id);
            st.setString(2, this.name);
            st.setString(3, this.location);
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
    }
    
    public void deleteTeam() {
        if (id != null) {
            PreparedStatement st = null;
            String q = "DELETE FROM team WHERE id = ?";

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
            System.out.println("Unable to delete team because id is null");
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
}
