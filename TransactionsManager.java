import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


	public class TransactionsManager {

		public void borrowBook(int bookId, int memberId) {
		    // Example query assuming foreign keys are involved
		    String query = "INSERT INTO transactions (book_id, memeber_id) VALUES (?, ?)";
		    
		    try (Connection conn = DatabaseConnection.getConnection();
		    	PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setInt(1, bookId);
		        pstmt.setInt(2, memberId);
		        pstmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace(); 
		    }
		}

	    

	    public void returnBook(int transactionId) {
	        String query = "UPDATE transactions SET return_date = ? WHERE id = ?";
	        String updateQuery = "UPDATE books SET available_copies = available_copies + 1 WHERE id = " +
	                             "(SELECT book_id FROM transactions WHERE id = ?)";
	        
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query);
	             PreparedStatement updatePstmt = conn.prepareStatement(updateQuery)) {
	            pstmt.setDate(1, new java.sql.Date(new Date().getTime()));
	            pstmt.setInt(2, transactionId);
	            pstmt.executeUpdate();
	            
	            updatePstmt.setInt(1, transactionId);
	            updatePstmt.executeUpdate();
	            System.out.println("Book returned successfully.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void listTransactions() {
	        String query = "SELECT * FROM transactions";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("Transaction ID: " + rs.getInt("id") + ", Book ID: " + rs.getInt("book_id") +
	                        ", Memeber ID: " + rs.getInt("memebers_id") + ", Issue Date: " + rs.getDate("issue_date") +
	                        ", Return Date: " + rs.getDate("return_date"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


