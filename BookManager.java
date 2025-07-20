import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookManager {

    public void addBook(String title, String author, int publishedYear, int availableCopies) {
        String query = "INSERT INTO books (title, author, published_year, available_copies) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, publishedYear);
            pstmt.setInt(4, availableCopies);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listBooks() {
        String query = "SELECT * FROM books";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Title: " + rs.getString("title") +
                        ", Author: " + rs.getString("author") + ", Year: " + rs.getInt("published_year") +
                        ", Available Copies: " + rs.getInt("available_copies"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkBookExists(int bookId) {
        String checkQuery = "SELECT COUNT(*) FROM books WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   

}
