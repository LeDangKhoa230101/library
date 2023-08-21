package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Book;
import model.User;

public class Dao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Dao() {
		super();
	}

	public Dao(Connection conn) {
		this.conn = conn;
	}

	public User login(String username, String password) {
		User user = new User();
		try {
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new  User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean checkUserExit(String username) {
		try {
			String query = "SELECT * FROM users WHERE username = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void register(String username, String password) {
		try {
			String query = "INSERT INTO users (username, password) VALUES (?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int totalBook() {
		int totalBook = 0;
		try {
			String query = "SELECT COUNT(*) AS total_books FROM books";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				totalBook = rs.getInt("total_books");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalBook;
	}

	public int bookIsAvailable() {
		int bookAvailable = 0;
		try {
			String query = "SELECT COUNT(*) AS total_available_books FROM books WHERE is_available = 1";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				bookAvailable = rs.getInt("total_available_books");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookAvailable;
	}
	
	public int bookBorrowed() {
		int bookBorrowed = 0;
		try {
			String query = "SELECT COUNT(*) AS total_borrowed_books FROM loans WHERE due_date IS NULL";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				bookBorrowed = rs.getInt("total_borrowed_books");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookBorrowed;
	}
	
	public List<Book> getBooks() {
		List<Book> listBook = new ArrayList<Book>();
		try {
			String query = "SELECT * FROM books";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				listBook.add(new Book(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getInt(5), 
								rs.getInt(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBook; 
	}
	
	public List<Book> searchBook(String keyword) {
		List<Book> listBook = new ArrayList<Book>();
		try {
			String query = "SELECT * FROM books WHERE title LIKE '%" + keyword + "%'";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				listBook.add(new Book(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getInt(5), 
								rs.getInt(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBook; 
	}
	
	public void addBook(String title, String author, String genre, int year, int quantity, int available) {
		try {
			String query = "INSERT INTO books (title, author, genre, publication_year, quantity, is_available) VALUES (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setString(3, genre);
			ps.setInt(4, year);
			ps.setInt(5, quantity);
			ps.setInt(6, available);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeBook(int bookId) {
		// Xác định các bản ghi loans liên quan
		List<Integer> loanIdsToDelete = new ArrayList<Integer>();
		try {
			String getLoansQuery = "SELECT loan_id FROM loans WHERE book_id = ?";
			ps = conn.prepareStatement(getLoansQuery);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int loanId = rs.getInt("loan_id");
				loanIdsToDelete.add(loanId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Xóa tất cả các bản ghi loans liên quan
		try {
			String deleteLoansQuery = "DELETE FROM loans WHERE loan_id = ?";
			ps = conn.prepareStatement(deleteLoansQuery);
			for(int loanId : loanIdsToDelete) {
				ps.setInt(1, loanId);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Tiến hành xóa cuốn sách từ bảng books
		try {
			String query = "DELETE FROM books WHERE book_id = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, bookId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateBook(String title, String author, 
			String genre, String year, String quantity) {
		try {
			String query = "UPDATE books SET title = ?, author = ?, genre = ?, publication_year = ?, quantity = ? WHERE title = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setString(3, genre);
			ps.setString(4, year);
			ps.setString(5, quantity);
			ps.setString(6, title);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> getBookAvailable() {
		List<Book> books = new ArrayList<Book>();
		try {
			String query = "SELECT * FROM books WHERE is_available = 1";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				books.add(new Book(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getInt(5), 
								rs.getInt(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	
	// ngày mượn là ngày hiện tại và ngày trả dự kiến là sau 14 ngày
	public void borrowBooks(int userId, List<Integer> bookIds) {
		try {
			for(Integer bookId : bookIds) {
				String query = "INSERT INTO loans (user_id, book_id, loan_date, due_date) "
						+ "VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY))";
				ps = conn.prepareStatement(query);
				ps.setInt(1, userId);
				ps.setInt(2, bookId); 
				ps.executeUpdate();
				
				String updateQty = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
				ps = conn.prepareStatement(updateQty);
				ps.setInt(1, bookId); 
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public List<Book> getBorrowedBooks(int userId) {
		List<Book> books = new ArrayList<Book>();
		try {
			String query = "SELECT loans.loan_id, books.title, books.author, books.genre, books.publication_year FROM books INNER JOIN loans ON books.book_id = loans.book_id WHERE loans.user_id = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId); 
			rs = ps.executeQuery();
			while(rs.next()) {
				books.add(new Book(rs.getInt(1),
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getInt(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public void returnBooks(List<Integer> loanIds) {
		try {
			String updateQuery = "UPDATE loans SET due_date = CURDATE() WHERE CONCAT(',', ?, ',') LIKE CONCAT('%,', loan_id, ',%')";

			try (PreparedStatement ps = conn.prepareStatement(updateQuery)) {
			    ps.setString(1, String.join(",", loanIds.stream().map(String::valueOf).collect(Collectors.toList())));
			    ps.executeUpdate();
			}
			
			// Xóa sách đã trả khỏi danh sách sách đang mượn của người dùng
	        String deleteQuery = "DELETE FROM loans WHERE loan_id IN (?)";

	        try (PreparedStatement psDelete = conn.prepareStatement(deleteQuery)) {
	            psDelete.setString(1, String.join(",", loanIds.stream().map(String::valueOf).collect(Collectors.toList())));
	            psDelete.executeUpdate();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
