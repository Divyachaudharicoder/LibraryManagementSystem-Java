import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemeberManager {

    public void addMemeber(String name, String email) {
        String query = "INSERT INTO memebers (name, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Memeber added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listMemebers() {
        String query = "SELECT * FROM memebers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                        ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkMemebersExists(int memeberName) {
        String checkQuery = "SELECT COUNT(*) FROM books WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {
            pstmt.setInt(1, memeberName);
            ResultSet rs = pstmt.executeQuery();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
