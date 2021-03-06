package com.frcscout.admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.frcscout.sql.DBConnection;
import com.frcscout.sql.ManageTestMySQLDatabase;
import com.frcscout.sql.TestMySQLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
public class EventBeanTest {

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

}
