package com.frcscout.admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.frcscout.admin.EventBean;
import com.frcscout.sql.DBConnection;
import com.frcscout.sql.ManageTestMySQLDatabase;
import com.frcscout.sql.TestMySQLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EventBeanTest {
    
    @Test
    public void loadEvent() 
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String id = "testID";
        String testName = "testName";
        String testLocation = "testLocation";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            EventBean event = new EventBean(dbconn);
            event.setName(testName);
            event.setLocation(testLocation);
            event.loadEvent(id);
            rs = st.executeQuery("select * from events limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("name"), testName);
            assertEquals(rs.getString("location"), testLocation);
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
    public void loadEvents()
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String testName = "testName";
        String testLocation = "testLocation";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            EventBean event = new EventBean(dbconn);
            event.setName(testName);
            event.setLocation(testLocation);
            event.setId(0);
            event.loadEvents();
            rs = st.executeQuery("select * from events limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("name"), testName);
            assertEquals(rs.getString("location"), testLocation);
            assertEquals(rs.getInt(0), 0);
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
    public void updateEvent()
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String expectedName = "testname";
        String expectedLocation = "testloc";
        String newName = "newTestName";
        String newLocation = "newTestLocation";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            EventBean event = new EventBean(dbconn);
            event.setId(0);
            event.setName(expectedName);
            event.setLocation(expectedLocation);
            event.insertEvent();
            event.setName(newName);
            event.setLocation(newLocation);
            event.updateEvent();
            rs = st.executeQuery("select * from events limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("name"), newName);
            assertEquals(rs.getString("location"), newLocation);
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
    public void insertEvent(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String expectedName = "testname";
        String expectedLocation = "testloc";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            EventBean event = new EventBean(dbconn);
            event.setName(expectedName);
            event.setLocation(expectedLocation);
            event.insertEvent();
            rs = st.executeQuery("select * from events limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("name"), expectedName);
            assertEquals(rs.getString("location"), expectedLocation);
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
    public void deleteEvent()
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String expectedName = "testname";
        String expectedLocation = "testloc";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            EventBean event = new EventBean(dbconn);
            event.setName(expectedName);
            event.setLocation(expectedLocation);
            event.setId(0);
            event.insertEvent();
            event.deleteEvent();
            rs = st.executeQuery("select * from events limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("name"), null);
            assertEquals(rs.getString("location"), null);
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
}
