package model;

public class Book {
	private int loanId;
	private int bookID;
	private String title;
	private String author;
	private String genre;
	private int year;
	private int quantity;
	private int isAvailable;

	public Book() {
		super();
	}
	
	public Book(String title, String author, String genre, int year) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
	}

	public Book(int loanId, String title, String author, String genre, int year) {
		super();
		this.loanId = loanId;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
	}

	public Book(int bookID, String title, String author, String genre, int year, int quantity) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
		this.quantity = quantity;
	}
	
	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", title=" + title + ", author=" + author + ", genre=" + genre + ", year="
				+ year + ", quantity=" + quantity + "]";
	}
	
}
