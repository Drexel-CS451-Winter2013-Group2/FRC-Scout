package test.com.frcscout.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.frcscout.sql.DBConnection;
import com.frcscout.sql.ManageTestMySQLDatabase;
import com.frcscout.sql.TestMySQLConnection;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ManageTestMySQLDatabaseTest {

    @Test
    public void testCreateDeleteDatabase() {
        Connection conn = null;
        Statement st = null;
        try {
            DBConnection dbconn = new TestMySQLConnection();
            conn = dbconn.getConnection();
            assertTrue(ManageTestMySQLDatabase.createDatabase(conn));
            st = conn.createStatement();
            st.execute("use frcscout_test");
        } catch (Exception e) {
            fail();
        } finally {
            try {
                ManageTestMySQLDatabase.deleteDatabase(conn);
                conn.close();
                st.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
            
        }
    }
}
