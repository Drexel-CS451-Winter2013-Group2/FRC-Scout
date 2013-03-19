package com.frcscout.admin;

import java.math.BigInteger;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;

import org.junit.Test;

import com.frcscout.admin.UserAccountBean;
import com.frcscout.sql.DBConnection;
import com.frcscout.sql.ManageTestMySQLDatabase;
import com.frcscout.sql.TestMySQLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserAccountBeanTest {

    @Test
    public void loadUser()
    {
    	Connection conn = null;
        Statement sts = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String q = "INSERT INTO users SET email= ?, password = MD5(?), first_name = ?, last_name = ?";
        String testFirst = "testFirst";
        String testLast = "testLast";
        String testEmail = "test123@gmail.com";
        String testPassword = "testing123";
        try {
        	DBConnection dbconn = new TestMySQLConnection();
			conn = ManageTestMySQLDatabase.createConnection();
			assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
			sts = conn.createStatement();
			sts.execute("use frcscout_test");
			st = conn.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, testEmail);
			st.setString(2, testPassword);
			st.setString(3, testFirst);
			st.setString(4, testLast);
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			assertTrue(rs.next());
            conn.commit();            
            
            UserAccountBean user = new UserAccountBean(dbconn);
            user.loadUser(Integer.toString(rs.getInt(1)));
            rs = st.executeQuery("select * from users limit 1");
            assertTrue(rs.next());
            assertEquals(user.getFirstName(), testFirst);
            assertEquals(user.getLastName(), testLast);
            assertEquals(user.getEmail(), testEmail);
        } catch (Exception e) {
        	e.printStackTrace();
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

    /*
	@Test
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
    }*/
    
    @Test
    public void updateUser()
    {
        Connection conn = null;
        Statement sts = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String q = "INSERT INTO users SET email= ?, password = MD5(?), first_name = ?, last_name = ?";
        String testFirst = "testFirst";
        String testLast = "testLast";
        String testEmail = "test123@gmail.com";
        String testPassword = "testing123";
        String newFirst = "newFirst";
        String newLast = "newLast";
        String newEmail = "new123@gmail.com";
        String newPassword = "newTesting123";
        try {
        	DBConnection dbconn = new TestMySQLConnection();
			conn = ManageTestMySQLDatabase.createConnection();
			assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
			sts = conn.createStatement();
			sts.execute("use frcscout_test");
			st = conn.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, testEmail);
			st.setString(2, testPassword);
			st.setString(3, testFirst);
			st.setString(4, testLast);
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			assertTrue(rs.next());
            conn.commit();
            
            UserAccountBean user = new UserAccountBean(dbconn);
            user.setId(rs.getInt(1));
            user.setFirstName(newFirst);
            user.setLastName(newLast);
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            user.setRole(1);
            user.updateUser();
            rs = st.executeQuery("select * from users limit 1");
            assertTrue(rs.next());
            assertEquals(rs.getString("first_name"), newFirst);
            assertEquals(rs.getString("last_name"), newLast);
            assertEquals(rs.getString("email"), newEmail);
            //System.out.println(rs.getString("password"));
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(newPassword.getBytes(), 0, newPassword.length());
            BigInteger big = new BigInteger(1, m.digest());
            String mPassword = big.toString(16);
            //System.out.println(mPassword);
            assertEquals(rs.getString("password"), mPassword);
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
    
    @Test
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
             user.setRole(1);
             user.insertUser();
             rs = st.executeQuery("select * from users limit 1");
             assertTrue(rs.next());
             assertEquals(rs.getString("first_name"), testFirst);
             assertEquals(rs.getString("last_name"), testLast);
             assertEquals(rs.getString("email"), testEmail);
             //System.out.println(rs.getString("password"));
             MessageDigest m = MessageDigest.getInstance("MD5");
             m.update(testPassword.getBytes(), 0, testPassword.length());
             BigInteger big = new BigInteger(1, m.digest());
             String mPassword = big.toString(16);
             //System.out.println(mPassword);
             assertEquals(rs.getString("password"), mPassword);
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
    public void deleteUser()
    {
    	 Connection conn = null;
         Statement sts = null;
         PreparedStatement st = null;
         ResultSet rs = null;
         String q = "INSERT INTO users SET email= ?, password = MD5(?), first_name = ?, last_name = ?";
         String testFirst = "testFirst";
         String testLast = "testLast";
         String testEmail = "test123@gmail.com";
         String testPassword = "testing123";
         try {
         	DBConnection dbconn = new TestMySQLConnection();
 			conn = ManageTestMySQLDatabase.createConnection();
 			assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
 			sts = conn.createStatement();
 			sts.execute("use frcscout_test");
 			st = conn.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
 			st.setString(1, testEmail);
 			st.setString(2, testPassword);
 			st.setString(3, testFirst);
 			st.setString(4, testLast);
 			st.executeUpdate();
 			rs = st.getGeneratedKeys();
 			assertTrue(rs.next());
            conn.commit();
             
            UserAccountBean user = new UserAccountBean(dbconn);
            user.setId(rs.getInt(1));
            user.deleteUser();
            rs = st.executeQuery("select * from users limit 1");
            assertTrue(rs.next());
            assertFalse(rs.getBoolean("active"));
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
