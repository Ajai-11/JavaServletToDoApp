// UserDao.java
import java.sql.*;

public class UserDao {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public UserDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public User validateUser(String email, String pass) {
        String sql = "SELECT * FROM users WHERE email = ? AND pass = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("pass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void storeUser(User u) {
        String sql = "INSERT INTO users(name, email, pass) VALUES(?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, u.getName());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPass());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
