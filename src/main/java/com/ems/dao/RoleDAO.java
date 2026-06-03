package com.ems.dao;
import com.ems.model.AuditLog;
import com.ems.util.DBConnection;
import java.sql.*;
/** DAO — RoleDAO (JDBC PreparedStatement implementation) */
public class RoleDAO {
    public void log(AuditLog log) throws SQLException {
        String sql = "INSERT INTO audit_logs (user_id, action, table_name, record_id, description) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, log.getUserId());
            ps.setString(2, log.getAction());
            ps.setString(3, log.getTableName());
            ps.setInt(4, log.getRecordId());
            ps.setString(5, log.getDescription());
            ps.executeUpdate();
        }
    }
}
