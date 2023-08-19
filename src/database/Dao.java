package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Book;

public class Dao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Dao(Connection conn) {
		this.conn = conn;
	}

	public boolean login(String username, String password) {
		try {
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
		try {
			String query = "DELETE FROM books WHERE book_id = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, bookId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
