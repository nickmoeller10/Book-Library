
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

public class Librarian {

	// instance fields
	private String username; // librarian's username
	private String password; // librarian password to have access to this application

	public Librarian(String name, String pass) {
		username = name;
		password = pass;
	}

	// Checks if a given password equals the password of this librarian
	public boolean checkPassword(String word) {
		if (word.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	// Returns the name of this librarian
	public String getUsername() {
		return username;
	}

}