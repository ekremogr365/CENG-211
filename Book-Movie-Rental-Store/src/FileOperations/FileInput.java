package FileOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import RentableItems.Movie;
import RentableItems.Book;

public class FileInput {

	public void transferBooksAndMovies(ArrayList<Movie> moviess,ArrayList<Book> books) throws IOException{
		File file = new File("MoviesAndBooks.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line =   "";
		while ((line = br.readLine()) != null) {
			String[] info = line.split(",");
			if(info[0].equalsIgnoreCase("Movie")) {
				moviess.add(new Movie(Integer.valueOf(info[1]),info[2], info[3], info[4], info[5]));
	
			}
			else if(info[0].equalsIgnoreCase("Book")) {
				books.add(new Book(Integer.valueOf(info[1]),info[2], info[3], info[4]));
			}

		}
		br.close();
		
	}
	
	
}
