package com.frcscout.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.frcscout.admin.TeamBean;
import com.frcscout.sql.DBConnection;
import com.frcscout.sql.ManageTestMySQLDatabase;
import com.frcscout.sql.TestMySQLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TeamBeanTest {

    @Test
    public void loadTeam()
    {
        Connection conn = null;
        Statement sts = null;
        PreparedStatement st = null;
        String q = "INSERT INTO team SET id = ?, name = ?";
        int testID = 0;
        String testName = "testName";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            sts = conn.createStatement();
            sts.execute("use frcscout_test");
            st = conn.prepareStatement(q);
            st.setInt(1, testID);
            st.setString(2, testName);
            st.executeUpdate();
            conn.commit();
            
            TeamBean team = new TeamBean(dbconn);
            team.loadTeam(Integer.toString(testID));
            assertEquals(team.getId(), testID);
            assertEquals(team.getName(), testName);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            try {
                ManageTestMySQLDatabase.deleteDatabase(conn);
                conn.close();
                sts.close();
                st.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        }
    }
    
    @Test
    public void updateTeam()
    {
        Connection conn = null;
        Statement sts = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String q = "INSERT INTO team SET id= ?, name = ?, location = ?";
        int expectedID = 0;
        String expectedName = "testname";
        String expectedLoc = "testLoc";
        int newID = 1;
        String newName = "newTestName";
        String newLoc = "newTestLoc";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            sts = conn.createStatement();
            sts.execute("use frcscout_test");
            st = conn.prepareStatement(q);
            st.setInt(1, expectedID);
            st.setString(2, expectedName);
            st.setString(3, expectedLoc);
            st.executeUpdate();
            conn.commit();
             
            TeamBean team = new TeamBean(dbconn);
            team.setId(expectedID);
            team.setName(newName);
            team.setLocation(newLoc);
            team.updateTeam(Integer.toString(newID));
            rs = st.executeQuery("select * from team limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getInt("id"), newID);
            assertEquals(rs.getString("name"), newName);
            assertEquals(rs.getString("location"), newLoc);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            try {
                ManageTestMySQLDatabase.deleteDatabase(conn);
                conn.close();
                sts.close();
                st.close();
                rs.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        }
    }
    
    @Test
    public void insertTeam()
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        int expectedID = 0;
        String expectedName = "testname";
        String expectedLoc = "testLoc";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            
            TeamBean team = new TeamBean(dbconn);
            team.setId(expectedID);
            team.setName(expectedName);
            team.setLocation(expectedLoc);
            team.insertTeam();
            rs = st.executeQuery("select * from team limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getInt("id"), expectedID);
            assertEquals(rs.getString("name"), expectedName);
            assertEquals(rs.getString("location"), expectedLoc);
        } catch (Exception e) {
            fail();
        } finally {
            try {
                ManageTestMySQLDatabase.deleteDatabase(conn);
                conn.close();
                st.close();
                rs.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
            
        }
    }
    
    @Test
    public void deleteTeam()
    {
        Connection conn = null;
        Statement sts = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String q = "INSERT INTO team SET id = ?, name = ?";
        int expectedID = 0;
        String expectedName = "testname";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            sts = conn.createStatement();
            sts.execute("use frcscout_test");
            st = conn.prepareStatement(q);
            st.setInt(1, expectedID);
            st.setString(2, expectedName);
            st.executeUpdate();
            conn.commit();
            
            TeamBean team = new TeamBean(dbconn);
            team.setId(expectedID);
            team.deleteTeam();
            rs = st.executeQuery("select * from team limit 1");
            assertFalse(rs.next());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            try {
                ManageTestMySQLDatabase.deleteDatabase(conn);
                conn.close();
                st.close();
                sts.close();
                rs.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
            
        }
    }
}
