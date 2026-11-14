package org.lucasmendss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static final String URL =
            "jdbc:sqlserver://localhost;databaseName=aulajava;integratedSecurity=true;encrypt=false;trustServerCertificate=true;";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do SQL Server não encontrado.", e);
        }
        return DriverManager.getConnection(URL);
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException ignored) {}

        try {
            if (stmt != null) stmt.close();
        } catch (SQLException ignored) {}

        try {
            if (conn != null) conn.close();
        } catch (SQLException ignored) {}
    }
}