
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BookLibrary
// Files:           Librarian, Subscriber, Book, BookLibraryTests, Library
// Course:          CS 300 2019
//
// Author:          Nick Moeller
// Email:           njmoeller@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Dylan Emert
// Partner Email:   demert@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Book {

	// class/static fields
	private static int nextId = 1; // represents the identifier of the next book

	// Instance fields
	private final int ID; // unique identifier of this book
	private String author; // name of the author of this book
	private String title; // title of this book
	private Integer borrowerCardBarCode; // card bar code of the borrower of this book
	// When borrowerCardBarCode == null, the book is available
	// (no one has the book)

	// Constructs a new book object and initializes its fields
	public Book(String book, String authorName) {
		title = book;
		author = authorName;
		borrowerCardBarCode = null;
		ID = nextId;
		++nextId;
	}

	// Records the borrower's card bar code of this book if the book is available.
	// If this book is not available, this method does nothing.
	public void borrowBook(Integer barCode) {
		if (isAvailable()) {
			borrowerCardBarCode = barCode;
		}
	}

	// Sets this book to be available. When the borrowerCardBarCode is set to null,
	// no one is borrowing it
	public void returnBook() {
		borrowerCardBarCode = null;
	}

	// Checks if book is available
	public boolean isAvailable() {
		if (borrowerCardBarCode == null) {
			return true;
		} else {
			return false;
		}
	}

	// Return the the borrower's card bar code of this book if the book has been
	// checked out or null if not
	public Integer getBorrowerCardBarCode() {
		return borrowerCardBarCode;
	}

	// Returns ID of book object
	public int getID() {
		return ID;
	}

	// Return the title of this book
	public String getTitle() {
		return title;
	}

	//Returns the author of this book
	public String getAuthor() {
		return author;
	}

}
