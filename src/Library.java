
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
import java.util.Scanner;

public class Library {
	// instance fields
	private String address; // Street address of this library
	private Librarian librarian; // this library's only librarian
	private ArrayList<Book> books; // list of the books in this library
	private ArrayList<Subscriber> subscribers; // list of this library's subscribers

	// Creates a new library and initializes all its fields
	public Library(String libAddress, String libUsername, String libLogin) {
		address = libAddress;
		librarian = new Librarian(libUsername, libLogin);
		books = new ArrayList<Book>();
		subscribers = new ArrayList<Subscriber>();
	}

	// add a new book to the libraries book list
	public void addBook(String title, String author) {
		Book bk = new Book(title, author);
		books.add(bk);
		System.out.println("Book with Title " + title + " is successfully added to the library.");
	}

	// adds a new Subscriber to the libraries subscriber list
	public void addSubscriber(String name, int pin, String address, String phoneNumber) {
		Subscriber sub = new Subscriber(name, pin, address, phoneNumber);
		subscribers.add(sub);
		System.out.println("Library card with bar code " + sub.getCARD_BAR_CODE()
				+ " is successfully issued to the new subscriber " + name + ".");
	}

	// Returns a book given the bookID, otherwise returns null
	public Book findBook(int bookID) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getID() == bookID) {
				return books.get(i);
			}
		}
		System.out.println("Error: this book identifier didn't match" + " any of our books identifiers.");
		return null;
	}

	// Returns a list of books given the book title
	public ArrayList<Book> findBookByTitle(String title) {
		title.toLowerCase();
		ArrayList<Book> bookArray = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle() == title) {
				bookArray.add(books.get(i));
			}
		}
		return bookArray;
	}

	// Returns a list of books given by the author name
	public ArrayList<Book> findBookByAuthor(String author) {
		author.toLowerCase();
		ArrayList<Book> bookArray = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getAuthor() == author) {
				bookArray.add(books.get(i));
			}
		}
		return bookArray;
	}

	// Finds and returns a subscriber given a bar code
	public Subscriber findSubscriber(int cardBarCode) {
		for (int i = 0; i < subscribers.size(); i++) {
			if (subscribers.get(i).getCARD_BAR_CODE() == cardBarCode) {
				return subscribers.get(i);
			}
		}
		System.out.println("Error: this card bar code didn't match any of our records.");
		return null;
	}

	// Returns address of this library
	public String getAddress() {
		return address;
	}

	// Returns the librarian of this library
	public Librarian getLibrarian() {
		return librarian;
	}

	// Removes a book given the book identifier from this library
	public Book removeBook(int bookID) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getID() == bookID) {
				books.remove(i);
			}
		}
		return null;
	}

	// Reads and processes the user commands with respect to the menu of this
	// application
	public void readProcessUserCommand(Scanner scanner) {
		final String promptCommandLine = "ENTER COMMAND: ";
		displayMainMenu(); // display the library management system main menu
		System.out.print(promptCommandLine);
		String command = scanner.nextLine(); // read user command line
		String[] commands = command.trim().split(" "); // split user command
		while (commands[0].trim().charAt(0) != '3') { // '3': Exit the application
			switch (commands[0].trim().charAt(0)) {
			case '1': // login as librarian commands[1]: password
				if (this.librarian.checkPassword(commands[1])) {
					// read and process librarian commands
					readProcessLibrarianCommand(scanner);
				} else { // error password
					System.out.println("Error: Password incorrect!");
				}
				break;
			case '2': // login as subscriber commands[1]: card bar code
				Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
				if (subscriber != null) {
					if (subscriber.getPin() == Integer.parseInt(commands[2])) // correct PIN
						// read and process subscriber commands
						readProcessSubscriberCommand(subscriber, scanner);
					else
						System.out.println("Error: Incorrect PIN.");
				}
				break;
			}
			// read and split next user command line
			displayMainMenu(); // display the library management system main menu
			System.out.print(promptCommandLine);
			command = scanner.nextLine(); // read user command line
			commands = command.trim().split(" "); // split user command line
		}
	}

	// Displays a list of books
	public static void displayBooks(ArrayList<Book> books) {
		// Traverse the list of books and display book id, title, author, and
		// availability of each book
		for (int i = 0; i < books.size(); i++) {
			System.out.print("<Book ID>: " + books.get(i).getID() + " ");
			System.out.print("<Title>: " + books.get(i).getTitle() + " ");
			System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
			System.out.println("<Is Available>: " + books.get(i).isAvailable());
		}
	}

	// Displays the main menu for this book library application
	private static void displayMainMenu() {
		System.out.println("\n--------------------------------------------------------");
		System.out.println("     Welcome to our Book Library Management System");
		System.out.println("--------------------------------------------------------");
		System.out.println("Enter one of the following options:");
		System.out.println("[1 <password>] Login as a librarian");
		System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
		System.out.println("[3] Exit"); // Exit the application
		System.out.println("--------------------------------------------------------");
	}

	// Displays the menu for a Subscriber
	private static void displaySubscriberMenu() {
		System.out.println("\n--------------------------------------------------------");
		System.out.println("    Welcome to Subscriber's Space");
		System.out.println("--------------------------------------------------------");
		System.out.println("Enter one of the following options:");
		System.out.println("[1 <book ID>] Check out a book");
		System.out.println("[2 <book ID>] Return a book");
		System.out.println("[3 <title>] Search a Book by title");
		System.out.println("[4 <author>] Search a Book by author");
		System.out.println("[5] Print list of books checked out");
		System.out.println("[6] Print history of returned books");
		System.out.println("[7 <address>] Update address");
		System.out.println("[8 <phone number>] Update phone number");
		System.out.println("[9] Logout");
		System.out.println("--------------------------------------------------------");
	}

	// Displays the menu for the Librarian
	private static void displayLibrarianMenu() {
		System.out.println("\n--------------------------------------------------------");
		System.out.println("    Welcome to Librarian's Space");
		System.out.println("--------------------------------------------------------");
		System.out.println("Enter one of the following options:");
		System.out.println("[1 <title> <author>] Add new Book");
		System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
		System.out.println("[3 <card bar code> <book ID>] Check out a Book");
		System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
		System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
		System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
		System.out.println("[7] Display All Books");
		System.out.println("[8 <book ID>] Remove a Book");
		System.out.println("[9] Logout");
		System.out.println("--------------------------------------------------------");
	}

	// Display the Application GoodBye and logout message.
	private static void displayGoodByeLogoutMessage() {
		System.out.println("\n--------------------------------------------------------");
		System.out.println("       Thanks for Using our Book Library App!!!!");
		System.out.println("--------------------------------------------------------");
	}

	// Reads and processes the librarian commands according to the librarian menu
	private void readProcessLibrarianCommand(Scanner scanner) {
		final String promptCommandLine = "ENTER COMMAND: ";
		displayLibrarianMenu();
		System.out.print(promptCommandLine);
		String command = scanner.nextLine(); // read user command line
		String[] commands = command.trim().split(" "); // split user command
		while (commands[0].trim().charAt(0) != '9') { // '9': Exit the application
			switch (commands[0].trim().charAt(0)) {
			case '1': // add new book
				addBook(commands[1], commands[2]);
				break;
			case '2': // add new subscriber
				int pin = Integer.parseInt(commands[2]);
				addSubscriber(commands[1], pin, commands[3], commands[4]);
				break;
			case '3': // checkout book
				int cardBarCode = Integer.parseInt(commands[1]);
				int id = Integer.parseInt(commands[2]);
				findSubscriber(cardBarCode).checkoutBook(findBook(id));
				break;
			case '4':// Returns book for subscriber
				cardBarCode = Integer.parseInt(commands[1]);
				id = Integer.parseInt(commands[2]);
				findSubscriber(cardBarCode).returnBook(findBook(id));
				break;
			case '5':// Display info of subscriber
				cardBarCode = Integer.parseInt(commands[1]);
				findSubscriber(cardBarCode).displayPersonalInfo();
				;
				break;
			case '6':// Display subscribers checked out books
				cardBarCode = Integer.parseInt(commands[1]);
				findSubscriber(cardBarCode).displayBooksCheckedOut();
				break;
			case '7':// Display books
				displayBooks(books);
				break;
			case '8':// Remove book given ID
				id = Integer.parseInt(commands[1]);
				removeBook(id);
				break;
			}
			displayLibrarianMenu();// Reprint menu
			System.out.println(promptCommandLine);
			command = scanner.nextLine(); // read user command line
			commands = command.trim().split(" "); // split user command
		}
	}

	// Reads and processes the subscriber commands according to the subscriber menu
	private void readProcessSubscriberCommand(Subscriber sub, Scanner Scanner) {
		final String promptCommandLine = "ENTER COMMAND: ";
		displaySubscriberMenu();
		System.out.print(promptCommandLine);
		String command = Scanner.nextLine(); // read user command line
		String[] commands = command.trim().split(" "); // split user command
		while (commands[0].trim().charAt(0) != '9') {
			switch (commands[0].trim().charAt(0)) {
			case ('1'):// Checkout book
				int id = Integer.parseInt(commands[1]);
				sub.checkoutBook(findBook(id));
				break;
			case ('2'):// Return book
				int id2 = Integer.parseInt(commands[1]);
				sub.returnBook(findBook(id2));
				break;
			case ('3'):// Find book by title
				findBookByTitle(commands[1]);
				break;
			case ('4'):// Find book by author
				findBookByAuthor(commands[1]);
				break;
			case ('5'):// Displays current books checked out
				sub.displayBooksCheckedOut();
				break;
			case ('6'):// Displays the history of books returned
				sub.displayHistoryBooksReturned();
				break;
			case ('7'):// Sets address of subscriber
				sub.setAddress(commands[1]);
				break;
			case ('8'):// Sets phone number of subscriber
				sub.setPhoneNumber(commands[1]);
				break;
			}
			displaySubscriberMenu();
			System.out.print(promptCommandLine);
			command = Scanner.nextLine(); // read user command line
			commands = command.trim().split(" "); // split user command line
		}
	}

	// Main method that represents the driver for this application
	public static void main(String[] args) {
		// Tests for this application
		// BookLibraryTests.testLibraryFindBookByAuthor();
		// BookLibraryTests.testCreateSubscriber();
		// BookLibraryTests.testBookConstructorGetters();
		// BookLibraryTests.testBookReturnBook();
		// BookLibraryTests.testSubscriberCheckoutBook();

		Scanner scanner = new Scanner(System.in); // create a scanner object to read user inputs
		// create a new library object
		Library madisonLibrary = new Library("Madison, WI", "april", "abc");
		madisonLibrary.readProcessUserCommand(scanner);// read and process user command lines
		displayGoodByeLogoutMessage(); // display good bye message
		scanner.close();// close this scanner
	}
}