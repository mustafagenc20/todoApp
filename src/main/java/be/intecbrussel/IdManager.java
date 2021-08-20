package be.intecbrussel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class IdManager {
    private String id;

    public Boolean checkId(String userId) throws SQLException {
        try {
            Statement statement = JDBCManager.getConnection().createStatement();
            String SQL = "SELECT * FROM user_mustafa";
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                id = rs.getString("id");
                if (id.equals(userId)) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return false;
    }
}
