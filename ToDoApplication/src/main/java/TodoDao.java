// TodoDao.java
import java.sql.*;

public class TodoDao {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public TodoDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieveData(int userId) {
        try {
            String sql = "SELECT * FROM todo WHERE user_id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, userId);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ToDo retrieveSingleData(int slno) {
        ToDo todo = null;
        String sql = "SELECT * FROM todo WHERE Slno = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, slno);
            rs = pst.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                boolean status = rs.getBoolean("status");
                todo = new ToDo();
                todo.setTitle(title);
                todo.setStatus(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }


    public void storeData(ToDo t, int userId) {
        String sql = "INSERT INTO todo (title, user_id) VALUES (?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getTitle());
            pst.setInt(2, userId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(int slno, ToDo t) {
        String sql = "UPDATE todo SET title = ?, status = ? WHERE Slno = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getTitle());
            pst.setBoolean(2, t.isStatus());
            pst.setInt(3, slno);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(int slno) {
        String sql = "DELETE FROM todo WHERE Slno = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, slno);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
