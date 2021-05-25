package books;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
	
	@Override
	public String toString() {
		return String.format(title + " by " + author + " (%d)", year);
	}
	
	public static ArrayList<Book> getList(String filePath) throws FileNotFoundException{
		//do I need to mention https://www.stackchief.com/blog/How%20to%20read%20a%20CSV%20file%20in%20Java%20%7C%20with%20examples
		// since I learned about buffered reader from them.
		
		ArrayList<Book> books = new ArrayList<Book>();
		String line;
		File file = new File(filePath);
		
		try (Scanner sc = new Scanner(file)){
			
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				String [] tokens = line.split("\\b,\\b");
				
				if (tokens.length == 3){
					String bookTitle = tokens[0];
					String bookAuthor = tokens[1];
					Integer bookYear = Integer.parseInt(tokens[2]);
					
					Book book = new Book(bookTitle, bookAuthor, bookYear);
					books.add(book);
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
	
	public int compareTo(Book b) {

		int compare = this.title.compareTo(b.title);
		
		if(compare == 0) {
			return this.author.compareTo(b.author);
		}
		return compare;
	}
	
	public static class ReverseAlphabeticalCompare implements Comparator<Book>{
		
		public int compare(Book a, Book b) {
			int compare = (a.title.compareTo(b.title)) * -1;
			
			if(compare == 0) {
				return (a.author.compareTo(b.author)) * -1;
			}
			return compare;
		}
	}
}
