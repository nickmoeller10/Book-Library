
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

import java.util.ArrayList;

public class Subscriber {
	// static fields
	private final static int MAX_BOOKS_CHECKED_OUT = 10; // maximum number of books to be checked
	// out by one subscriber
	private static int nextCardBarCode = 2019000001; // class variable that represents the card bar
	// code of the next subscriber to be created

	// Instance fields
	private int pin; // 4-digit pin to verify identity of subscriber
	private final Integer CARD_BAR_CODE; // card bar code of this subscriber
	private String name; // name of this subscriber
	private String address; // address of this subscriber
	private String phoneNumber; // phone number of this subscriber
	private ArrayList<Book> booksCheckedOut; // list of books checked out by this subscriber and
	// not yet returned. A subscriber can have at most 10 checked out books
	private ArrayList<Book> booksReturned; // list of the books returned by this subscriber

	// Creates a new subscriber with given name, address, and phone number,
	// and initializes its other instance fields
	public Subscriber(String subName, int subPin, String subAddress, String subPhoneNumber) {
		name = subName;
		pin = subPin;
		address = subAddress;
		phoneNumber = subPhoneNumber;
		booksCheckedOut = new ArrayList<Book>();
		booksReturned = new ArrayList<Book>();
		CARD_BAR_CODE = nextCardBarCode;
		++nextCardBarCode; // Increments bar code for next subscriber
	}

	// Checks out an available book
	public void checkoutBook(Book checkoutBook) {
		// Prints if subscriber has max books checked out
		if (booksCheckedOut.size() >= MAX_BOOKS_CHECKED_OUT) {
			System.out.println("Checkout Failed: You cannot check out more than " + MAX_BOOKS_CHECKED_OUT + " books.");
		}
		// Prints if subscriber already has book
		else if (booksCheckedOut.contains(checkoutBook)) {
			System.out.println("You have already checked out " + checkoutBook.getTitle() + " book.");
		}

		// Prints if book is not available for checkout
		else if (!checkoutBook.isAvailable()) {
			System.out.println("Sorry, " + checkoutBook.getTitle() + " is not available.");
		}
		// Adds a book to booksCheckedOut list
		else {
			booksCheckedOut.add(checkoutBook);
			checkoutBook.borrowBook(CARD_BAR_CODE);
		}
	}

	// Returns this subscribers address
	public String getAddress() {
		return address;
	}

	// Returns this subscribers Card Bar Code
	public Integer getCARD_BAR_CODE() {
		return CARD_BAR_CODE;
	}

	// Gets this subscribers name
	public String getName() {
		return name;
	}

	// Gets this subscribers phone number
	public String getPhoneNumber() {
		return phoneNumber;
	}

	// Gets this subscribers pin
	public int getPin() {
		return pin;
	}

	// Checks if Book has been returned
	public boolean isBookInBooksReturned(Book book) {
		return booksReturned.contains(book);
	}

	// Checks if book has been checked out
	public boolean isBookInBooksCheckedOut(Book book) {
		return booksCheckedOut.contains(book);
	}

	// Returns a book and removes it from checkout
	public void returnBook(Book book) {
		booksReturned.add(book);
		booksCheckedOut.remove(book);
		book.returnBook();
	}

	// Sets subscribers address
	public void setAddress(String subAddress) {
		address = subAddress;
	}

	// Sets subscribers phone number
	public void setPhoneNumber(String setPhoneNumber) {
		phoneNumber = setPhoneNumber;
	}

	// Displays the list of the books checked out and not yet returned
	public void displayBooksCheckedOut() {
		if (booksCheckedOut.isEmpty()) // empty list
			System.out.println("No books checked out by this subscriber");
		else
			// Traverse the list of books checked out by this subscriber and display its
			// content
			for (int i = 0; i < booksCheckedOut.size(); i++) {
				System.out.print("Book ID: " + booksCheckedOut.get(i).getID() + " ");
				System.out.print("Title: " + booksCheckedOut.get(i).getTitle() + " ");
				System.out.println("Author: " + booksCheckedOut.get(i).getAuthor());
			}
	}

	// Displays the history of the returned books by this subscriber
	public void displayHistoryBooksReturned() {
		if (booksReturned.isEmpty()) // empty list
			System.out.println("No books returned by this subscriber");
		else
			// Traverse the list of borrowed books already returned by this subscriber and
			// display its
			// content
			for (int i = 0; i < booksReturned.size(); i++) {
				System.out.print("Book ID: " + booksReturned.get(i).getID() + " ");
				System.out.print("Title: " + booksReturned.get(i).getTitle() + " ");
				System.out.println("Author: " + booksReturned.get(i).getAuthor());
			}
	}

	// Displays subscribers personal information
	public void displayPersonalInfo() {
		System.out.println("Personal information of the subscriber: " + CARD_BAR_CODE);
		System.out.println("  Name: " + name);
		System.out.println("  Address: " + address);
		System.out.println("  Phone number: " + phoneNumber);
	}
}