package books;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
/***
 * {@code Book} stores the information for a book.
 * 
 * @param title: the title of a book
 * @param author: the author of a book
 * @param year: the year a book was written
 * 
 * @author Malcolm Bailey
 * 
 */
public class Book implements Comparable<Book>{
	
	private String title;
	private String author;
	private int year;

	Book (String bookTitle, String bookAuthor, int bookYear){
		title = bookTitle;
		author = bookAuthor;
		year = bookYear;		
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getYear() {
		return year;
	}
	/***
	 * Displays book object as a string.
	 */
	@Override
	public String toString() {
		return String.format(title + " by " + author + " (%d)", year);
	}
	
	/***
	 * Reads a list of books from a csv file.
	 * @param filePath: the path of the csv file
	 * @return returns an ArrayList containing each book read from the csv file at {@code filePath}
	 * @throws FileNotFoundException if no csv file is found at the given {@code filePath}
	 */
	public static ArrayList<Book> getList(String filePath) throws FileNotFoundException{
		
		ArrayList<Book> books = new ArrayList<Book>();
		String line;
		File file = new File(filePath);
		
		try (Scanner sc = new Scanner(file)){
			
			while(sc.hasNextLine()) {
				//while there is another line, load the next line.
				line = sc.nextLine();
				//generate the individual tokens based on commas that have no word boundaries on either side.
				String [] tokens = line.split("\\b,\\b");
				
				if (tokens.length == 3){
					//if there are 3 total tokens that means the format is most likely correct (title, author, year)
					// this means we can store the various parts in a book object and add it to the ArrayList.
					String bookTitle = tokens[0];
					String bookAuthor = tokens[1];
					try {
						//Check that the third token is in fact an int, and add it to the list if is.
						Integer bookYear = Integer.parseInt(tokens[2]);
						Book book = new Book(bookTitle, bookAuthor, bookYear);
						books.add(book);
					} catch (NumberFormatException e) {
						System.err.println("Failed to read the following line: " + line + "\n" 
								+ "Please confirm that the third token is a number.");
					}

				} else {
					System.err.println("Failed to read the following line: " + line + "\n" 
							+ "Please confirm that the format is \"Title, Author, Year\".");
				}
			}	
			return books;
			
		} catch (Exception FileNotFoundException) {
			
			throw new FileNotFoundException("Could not find " + file);
		}
	}
	/***
	 * Compares book object to another book {@code b} first on title then on author
	 * @param b: a book object to be compared to.
	 * @return negative int if the title of book {@code b} is alphabetically greater than the book, 0 if they're equal, and positive 
	 * if book {@code b} is less than the book.
	 */
	public int compareTo(Book b) {

		int compare = this.title.compareTo(b.title);
		
		if(compare == 0) {
			return this.author.compareTo(b.author);
		}
		return compare;
	}
	
	
	/***
	 * Implements the Comparator's compare method for book objects to sort in reverse alphabetical order.
	 * @author malc
	 *
	 */
	public static class ReverseAlphabeticalCompare implements Comparator<Book>{
		
		/**
		 * Compares two books using reverse alphabetical order, first with title and then with author if two titles are the same.
		 * @param a,b the books to be compared to each other.
		 * @return negative int if the book {@code b} is alphabetically greater than book {@code a}, 0 if they're equal, 
		 * and positive if book {@code b} is less than book {@code a}.
		 */
		public int compare(Book a, Book b) {
			int compare = (a.title.compareTo(b.title)) * -1;
			
			if(compare == 0) {
				return (a.author.compareTo(b.author)) * -1;
			}
			return compare;
		}
	}
}
