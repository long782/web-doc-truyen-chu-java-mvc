package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoiCSDL {
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=QLWDT;encrypt=false;";
    private static String user = "sa"; 
    private static String password = "123"; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối CSDL SQL Server thành công!");
        } catch (Exception e) {
            System.out.println("Kết nối CSDL thất bại: " + e.getMessage());
        }
        return conn;
    }
    public static void main(String[] args) {
    	KetNoiCSDL.getConnection();
	}
}
