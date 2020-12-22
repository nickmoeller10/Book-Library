
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

public class BookLibraryTests {
	// Private static instance fields
	private static Subscriber scb;
	private static Book bk;

	// Tests if Book can be is added by get methods
	public static boolean testBookConstructorGetters() {
		int id1;
		int id2;
		// Create new book
		bk = new Book("Nick Moeller Auto Bio", "Nick Moeller");
		// Checks if can be retrieved by author name
		if (bk.getAuthor() == null) {
			System.out.println("FAILED: Did not get author");
			return false;
		}
		// Checks if cab be retrieved by Title
		if (bk.getTitle() == null) {
			System.out.println("FAILED: Did not get author");
			return false;
		}
		// Checks if Book ID is correct
		id1 = bk.getID();
		bk = new Book("Dylan Emert Auto Bio", "Dylan Emert");
		id2 = bk.getID();
		if (id1 == (id2 - 1)) {
			System.out.println("PASSED");
			return true;
		}
		return false;
	}

	// Tests if Book is returned
	public static boolean testBookReturnBook() {
		// Creates new book
		bk = new Book("Dylan Emert Bio", "Emert");
		bk.borrowBook(2019000001);
		bk.returnBook();
		// If book is available, test passes
		if (bk.isAvailable() == true) {
			System.out.println("PASSED");
			return true;
		}
		System.out.println("FAILED");
		return false;
	}

	// Tests if Subscriber can checkout book
	public static boolean testSubscriberCheckoutBook() {
		boolean isThere = false;
		// Creates new subscriber
		scb = new Subscriber("Dylan Emert", 1234, "12 Langdon St.", "913-549-7651");
		for (int i = 0; i < 10; i++) {
			bk = new Book("1" + i, "Dylan");
			scb.checkoutBook(bk);
			// Checks if there is a checked out book
			isThere = scb.isBookInBooksCheckedOut(bk);
			if (isThere == false) {
				System.out.println("FAILED: BookInBooksCheckedOut");
				return false;
				// Checks if bar code is null
			} else if (scb.getCARD_BAR_CODE() == null) {
				System.out.println("FAILED: getCARD_BAR_CODE");
				return false;
			}
		}
		bk = new Book("2", "Dylan");
		scb.checkoutBook(bk);
		// Adds another book and performs same tests
		isThere = scb.isBookInBooksCheckedOut(bk);
		if (isThere == true) {
			System.out.println("FAILED: isBookInBooksCheckedOut2");
			return false;
		}
		System.out.println("PASSED");
		return true;
	}

	// Tests is library can find a book by author
	public static boolean testLibraryFindBookByAuthor() {
		// Creates book list
		ArrayList<Book> bookAr = new ArrayList<Book>();
		Library lib = new Library("Madison, WI", "april", "abc");
		lib.addBook("CompSci", "Dylan");
		bookAr = lib.findBookByAuthor("Dylan");
		// Adds a book then iterates through list finding book that matches author
		for (int i = 0; i < bookAr.size(); i++) {
			if (bookAr.get(i).getAuthor() != "Dylan") {
				System.out.println("Fails");
				return false;
			}
		}
		System.out.println("PASSED");
		return true;
	}

	// Checks if subscriber is created successfully
	public static boolean testCreateSubscriber() {
		// Create subscriber
		Subscriber Nick = new Subscriber("Nick", 1234, "NorthPole", "6122145991");
		// Checks subscriber by name
		if (!Nick.getName().equals("Nick")) {
			System.out.println("FAILED: Could not retrieve name");
			return false;
		}
		// Checks subscriber by pin
		if (Nick.getPin() != 1234) {
			System.out.println("FAILED: Could not retrieve pin");
			return false;
		}
		// Checks subscriber by address
		if (!Nick.getAddress().equals("NorthPole")) {
			System.out.println("FAILED: Could not retrieve address");
			return false;
		}
		// Checks subscriber by phone number
		if (!Nick.getPhoneNumber().equals("6122145991")) {
			System.out.println("FAILED: Could not retrieve phone number");
			return false;
		}
		System.out.println("PASSED");
		return true;
	}
}
