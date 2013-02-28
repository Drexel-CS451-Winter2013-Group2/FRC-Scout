package com.frcscout.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class ManageTestMySQLDatabase{
    
    public static Connection createConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "first"; 
        String password = "sc0ut";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,userName,password);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean createDatabase(Connection dbconn) {
        Connection conn = null;
        try {
            conn = dbconn;
            Statement st = conn.createStatement();
            st.executeUpdate("DROP SCHEMA IF EXISTS frcscout_test");
            st.executeUpdate("CREATE SCHEMA IF NOT EXISTS frcscout_test");
            st.execute("use frcscout_test");
            conn.setAutoCommit(false);
            st.executeUpdate("DROP TABLE IF EXISTS `game`");
            st.executeUpdate("CREATE TABLE `game` ( " +
                    "`id` int(10) NOT NULL AUTO_INCREMENT," +
                    "`name` varchar(100) NOT NULL," +
                    "PRIMARY KEY (`id`), UNIQUE KEY `id_UNIQUE` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1");
            st.executeUpdate("INSERT INTO `game` VALUES (1,'Ultimate Ascent')");
            st.executeUpdate("DROP TABLE IF EXISTS `roles`");
            st.executeUpdate("CREATE TABLE `roles` (" +
                    "`name` varchar(30) NOT NULL," +
                    "PRIMARY KEY (`name`)) ENGINE=InnoDB DEFAULT CHARSET=latin1");
            st.executeUpdate("INSERT INTO `roles` VALUES ('administrator'),('scout'),('team_member')");
            st.executeUpdate("DROP TABLE IF EXISTS `team`");
            st.executeUpdate("CREATE TABLE `team` (" +
                    "`id` int(11) NOT NULL," +
                    "`name` varchar(250) NOT NULL," +
                    "`location` varchar(250) DEFAULT NULL," +
                    "PRIMARY KEY (`id`), UNIQUE KEY `id_UNIQUE` (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1");
            st.executeUpdate("DROP TABLE IF EXISTS `users`");
            st.executeUpdate("CREATE TABLE `users` (" +
                    "`email` varchar(254) NOT NULL," +
                    "`id` int(11) NOT NULL AUTO_INCREMENT," +
                    "`password` varchar(254) NOT NULL," +
                    "`first_name` varchar(50) DEFAULT NULL," +
                    "`last_name` varchar(50) DEFAULT NULL," +
                    "`active` tinyint(1) NOT NULL DEFAULT '1'," +
                    "PRIMARY KEY (`email`),  UNIQUE KEY `id_UNIQUE` (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1");
            st.executeUpdate("DROP TABLE IF EXISTS `user_roles`");
            st.executeUpdate("CREATE TABLE `user_roles` (" +
                    "`email` varchar(254) NOT NULL," +
                    "`roles` varchar(30) NOT NULL," +
                    "PRIMARY KEY (`email`,`roles`),  KEY `FK_email_idx` (`email`),  KEY `FK_roles_idx` (`roles`), " +
                    "CONSTRAINT `FK_email` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "CONSTRAINT `FK_roles` FOREIGN KEY (`roles`) REFERENCES `roles` (`name`) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1");

            st.executeUpdate("DROP TABLE IF EXISTS `events`");
            st.executeUpdate("CREATE TABLE `events` " +
                    "( `id` int(11) NOT NULL AUTO_INCREMENT," +
                   "`name` varchar(100) NOT NULL," +
                   "`location` varchar(250) DEFAULT NULL," +
                   "`game_id` int(11) NOT NULL DEFAULT '1'," +
                   "`start_date` date DEFAULT NULL," +
                   "`end_date` date DEFAULT NULL," +
                   "PRIMARY KEY (`id`), UNIQUE KEY `id_UNIQUE` (`id`), KEY `id_idx` (`game_id`)," +
                   "CONSTRAINT `FK_gameid` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) " +
                   "ON DELETE NO ACTION ON UPDATE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=latin1");
            st.executeUpdate("DROP TABLE IF EXISTS `match`");
            st.executeUpdate("CREATE TABLE `match` (" +
                    "`id` int(11) NOT NULL AUTO_INCREMENT," +
                    "`match_number` int(11) NOT NULL," +
                    "`event_id` int(11) NOT NULL," +
                    "PRIMARY KEY (`id`), UNIQUE KEY `id_UNIQUE` (`id`), KEY `FK_eventid_idx` (`event_id`)," +
                    "CONSTRAINT `FK_eventid` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
            st.executeUpdate("DROP TABLE IF EXISTS `match_record_2013`");
            st.executeUpdate("CREATE TABLE `match_record_2013` (" +
                    "`id` int(11) NOT NULL AUTO_INCREMENT," +
                    "`user` varchar(254) NOT NULL," +
                    "`team_id` int(11) NOT NULL," +
                    "`match_id` int(11) NOT NULL," +
                    "`color` varchar(10) NOT NULL," +
                    "`auton_top` int(11) NOT NULL," +
                    "`auton_middle` int(11) NOT NULL," +
                    "`auton_bottom` int(11) NOT NULL," +
                    "`teleop_top` int(11) NOT NULL," +
                    "`teleop_middle` int(11) NOT NULL," +
                    "`teleop_bottom` int(11) NOT NULL," +
                    "`teleop_pyramid` int(11) NOT NULL," +
                    "`pyramid_level` int(11) NOT NULL," +
                    "`play_style` varchar(20) NOT NULL," +
                    "`confidence` int(11) NOT NULL," +
                    "`ability` int(11) NOT NULL," +
                    "`fouls` binary(1) NOT NULL," +
                    "`technical_fouls` binary(1) NOT NULL," +
                    "`comments` blob," +
                    "`path` blob," +
                    "PRIMARY KEY (`id`), UNIQUE KEY `id_UNIQUE` (`id`), KEY `id_idx` (`team_id`)," +
                    "KEY `id_idx1` (`match_id`),  KEY `FK_enteruser_idx` (`user`),  CONSTRAINT `FK_enteruser` FOREIGN KEY (`user`) REFERENCES `users` (`email`) ON DELETE NO ACTION ON UPDATE CASCADE," +
                    "CONSTRAINT `FK_matchid` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE," +
                    "CONSTRAINT `FK_teamid` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException a) {
                a.printStackTrace();
            }
            return false;
        }
    }

    public static boolean deleteDatabase(Connection dbconn) {
        try {
            Connection conn = dbconn;
            Statement st = conn.createStatement();
            st.executeUpdate("DROP SCHEMA IF EXISTS frcscout_test");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
