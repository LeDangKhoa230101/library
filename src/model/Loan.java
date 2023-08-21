package model;

import java.util.Date;

public class Loan {
	private int loanID;
	private User userID;
	private Book bookID;
	private Date loanDate;
	private Date dueDate;

	public Loan(int loanID, User userID, Book bookID, Date loanDate, Date dueDate) {
		super();
		this.loanID = loanID;
		this.userID = userID;
		this.bookID = bookID;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}

	public Book getBookID() {
		return bookID;
	}

	public void setBookID(Book bookID) {
		this.bookID = bookID;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
