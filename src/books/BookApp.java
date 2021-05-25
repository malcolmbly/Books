package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import books.Book.ReverseAlphabeticalCompare;

/***
 * Reads a list of books from a given CSV file path,
 * then sorts the books based on their titles and authors
 * @author Malcolm Bailey
 *
 */
public class BookApp {

	public static void main(String[] args) {
		//Open a scanner to accept the file path of the csv file.
		ArrayList<Book> books = new ArrayList<Book>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter path to book csv file: ");
		String filePath = sc.nextLine();
		sc.close();
		
		try {
			//Runs the getList method to add all the books listed in the CSV file into a list.
			books = Book.getList(filePath);
			System.out.println("Successfully added " + books.size() + " books to the list.");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		// Sorts the book using both the natural order of the compareTo method
		Collections.sort(books);
		System.out.println("Books sorted by natural order:");
		for (Book book : books) {
			System.out.println(book);
		}
		
		for (int i = 0; i<3; i++) {
			System.out.println();	
		}
		//Sorts the book using the reverse alphabetical order from a comparator.
		ReverseAlphabeticalCompare reverseAlphabeticalCompare = new ReverseAlphabeticalCompare();
		Collections.sort(books, reverseAlphabeticalCompare);
		System.out.println("Books sorted by reverse alphabetical (natural) order:");
		for (Book book : books) {
			System.out.println(book);
		}
		
	}

}
