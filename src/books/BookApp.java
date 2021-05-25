package books;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		ArrayList<Book> books = new ArrayList<Book>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter path to book csv file: ");
		String filePath = sc.nextLine();
		
		try {
			books = Book.getList(filePath);
			System.out.println("Successfully added " + books.size() + " books to the list.");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		Collections.sort(books);
		System.out.println("Books sorted by natural order:");
		for (Book book : books) {
			System.out.println(book);
		}

	}

}
