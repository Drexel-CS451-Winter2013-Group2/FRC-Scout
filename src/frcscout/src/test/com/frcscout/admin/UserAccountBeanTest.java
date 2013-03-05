package test.com.frcscout.admin;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.frcscout.admin.UserAccountBean;
import com.frcscout.sql.DBConnection;
import com.frcscout.sql.ManageTestMySQLDatabase;
import com.frcscout.sql.TestMySQLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserAccountBeanTest {

    public void loadUser()
    {
    	Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String id = "testID";
        String testFirst = "testFirst";
        String testLast = "testLast";
        String testEmail = "test123@gmail.com";
        String testPassword = "testing123";
        try {
        	DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            UserAccountBean user = new UserAccountBean(dbconn);
            user.setFirstName(testFirst);
            user.setLastName(testLast);
            user.setEmail(testEmail);
            user.setPassword(testPassword);
            user.loadUser(id);
            rs = st.executeQuery("select * from users limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("first_name"), testFirst);
            assertEquals(rs.getString("last_name"), testLast);
            assertEquals(rs.getString("email"), testEmail);
            assertEquals(rs.getString("password"), testPassword);
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

    public void loadUsers()
    {
    	Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String testFirst = "testFirst";
        String testLast = "testLast";
        String testEmail = "test123@gmail.com";
        String testPassword = "testing123";
        try {
        	DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            UserAccountBean user = new UserAccountBean(dbconn);
            user.setFirstName(testFirst);
            user.setLastName(testLast);
            user.setEmail(testEmail);
            user.setPassword(testPassword);
            user.setId(0);
            user.loadUsers();
            rs = st.executeQuery("select * from users limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("first_name"), testFirst);
            assertEquals(rs.getString("last_name"), testLast);
            assertEquals(rs.getString("email"), testEmail);
            assertEquals(rs.getString("password"), testPassword);
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
    
    public void updateUser()
    {
    	Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String testFirst = "testFirst";
        String testLast = "testLast";
        String testEmail = "test123@gmail.com";
        String testPassword = "testing123";
        String newFirst = "newFirst";
        String newLast = "newLast";
        String newEmail = "new123@gmail.com";
        String newPassword = "new123";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            UserAccountBean user = new UserAccountBean(dbconn);
            user.setFirstName(testFirst);
            user.setLastName(testLast);
            user.setEmail(testEmail);
            user.setPassword(testPassword);
            user.setId(0);
            user.insertUser();
            user.setFirstName(newFirst);
            user.setLastName(newLast);
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            user.updateUser();
            rs = st.executeQuery("select * from users limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("first_name"), newFirst);
            assertEquals(rs.getString("last_name"), newLast);
            assertEquals(rs.getString("email"), newEmail);
            assertEquals(rs.getString("password"), newPassword);
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
    
    public void insertUser()
    {
    	 Connection conn = null;
         Statement st = null;
         ResultSet rs = null;
         String testFirst = "testFirst";
         String testLast = "testLast";
         String testEmail = "test123@gmail.com";
         String testPassword = "testing123";
         try {
             DBConnection dbconn = new TestMySQLConnection();
             conn = ManageTestMySQLDatabase.createConnection();
             assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
             st = conn.createStatement();
             st.execute("use frcscout_test");
             UserAccountBean user = new UserAccountBean(dbconn);
             user.setFirstName(testFirst);
             user.setLastName(testLast);
             user.setEmail(testEmail);
             user.setPassword(testPassword);
             user.setRole(0);
             user.insertUser();
             rs = st.executeQuery("select * from users limit 1");
             assertTrue(rs.next());
             assertEquals(rs.getString("first_name"), testFirst);
             assertEquals(rs.getString("last_name"), testLast);
             assertEquals(rs.getString("email"), testEmail);
             assertEquals(rs.getString("password"), testPassword);
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
    
    public void deleteUser()
    {
    	Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String testFirst = "testFirst";
        String testLast = "testLast";
        String testEmail = "test123@gmail.com";
        String testPassword = "testing123";
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = ManageTestMySQLDatabase.createConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
            UserAccountBean user = new UserAccountBean(dbconn);
            user.setFirstName(testFirst);
            user.setLastName(testLast);
            user.setEmail(testEmail);
            user.setPassword(testPassword);
            user.setId(0);
            user.insertUser();
            user.deleteUser();
            rs = st.executeQuery("select * from users limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("first_name"), testFirst);
            assertEquals(rs.getString("last_name"), testLast);
            assertEquals(rs.getString("email"), testEmail);
            assertEquals(rs.getString("password"), testPassword);
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
}
