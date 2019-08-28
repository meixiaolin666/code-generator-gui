package personal.mxl;

import java.sql.*;
import java.util.Properties;

public  class JdbcUtils {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "";
    private static String USER = "";
    private static String PASS = "";
    private static Connection conn;
    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        JdbcUtils.URL = URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        JdbcUtils.USER = USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static void setPASS(String PASS) {
        JdbcUtils.PASS = PASS;
    }

    public static void close(Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof ResultSet) {
            try {
                ((ResultSet) o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof Statement) {
            try {
                ((Statement) o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof Connection) {
            Connection c = (Connection) o;
            try {
                if (!c.isClosed()) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close() {
        close(conn);
    }

    public static DatabaseMetaData getDataBaseInfo() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Properties props =new Properties();
        props.put("remarksReporting","true");
        props.put("user", USER);
        props.put("password", PASS);
        conn =DriverManager.getConnection(URL,props);
        return conn.getMetaData();
    }

}  