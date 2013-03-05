package test.com.frcscout.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.frcscout.admin.TeamBean;
import com.frcscout.sql.DBConnection;
import com.frcscout.sql.ManageTestMySQLDatabase;
import com.frcscout.sql.TestMySQLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TeamBeanTest {

    public void loadTeam()
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
            TeamBean team = new TeamBean(dbconn);
            team.setName(testName);
            team.setLocation(testLocation);
            team.loadTeam(id);
            rs = st.executeQuery("select * from teams limit 1");
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

    public void loadTeams()
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
            TeamBean team = new TeamBean(dbconn);
            team.setName(testName);
            team.setLocation(testLocation);
            team.setId(0);
            team.loadTeams();
            rs = st.executeQuery("select * from teams limit 1");
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
    
    public void updateTeam()
    {
    	Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String expectedName = "testname";
        String expectedLocation = "testloc";
        String id = "testID";
        String newName = "newTestName";
        String newLocation = "newTestLocation";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            TeamBean team = new TeamBean(dbconn);
            team.setId(0);
            team.setName(expectedName);
            team.setLocation(expectedLocation);
            team.insertTeam();
            team.setName(newName);
            team.setLocation(newLocation);
            team.updateTeam(id);
            rs = st.executeQuery("select * from teams limit 1");
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
    
    public void insertTeam()
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
            TeamBean team = new TeamBean(dbconn);
            team.setName(expectedName);
            team.setLocation(expectedLocation);
            team.insertTeam();
            rs = st.executeQuery("select * from teams limit 1");
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
    
    public void deleteTeam()
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
            TeamBean team = new TeamBean(dbconn);
            team.setName(expectedName);
            team.setLocation(expectedLocation);
            team.setId(0);
            team.insertTeam();
            team.deleteTeam();
            rs = st.executeQuery("select * from teams limit 1");
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
