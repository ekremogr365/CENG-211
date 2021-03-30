package RentalStroe;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;

import FileOperations.FileInput;
import FileOperations.FileOutput;
import FileOperations.FileOutput.SaveTypes;
import RentableItems.Book;
import RentableItems.Movie;

public class BookAndMovieRentalStore {
	enum Policy{
		 NewReleasePolicy(10,5),
		 OldReleasePolicy(8,4),
		 BargainPolicy(5,1);
		 private final double charge;
		 private final double overdueCharge;
		 
		 Policy(double charge, double overdueCharge) {
			 this.charge = charge;
			 this.overdueCharge = overdueCharge;
		 }
		 public double charge() {return charge;}
		 public double overdueCharge() {return overdueCharge;}
		 
	}
	
	
	ArrayList<Invoce> ýnvoces;
	ArrayList<Movie> moviess = new ArrayList<>();
	ArrayList<Book> books = new ArrayList<>();
	ArrayList<Costumer> costumers;
	Policy policy;
	FileInput fileýn = new FileInput();
	
	public BookAndMovieRentalStore(ArrayList<Costumer> costumers, Policy policy) throws IOException {
		this.costumers = costumers;
		this.policy = policy;
		ýnvoces = new ArrayList<>();
		fileýn.transferBooksAndMovies(moviess, books);
	}
	
	
	public void rent(int costumerId, String typeOfItem, int ýtemId, Date rentDate) {
		Costumer costumer = findCostumer(costumers, costumerId);

		if(typeOfItem.equalsIgnoreCase("MOVÝE")){
			rentMovie(costumer, ýtemId, rentDate);
		}	
		else if(typeOfItem.equalsIgnoreCase("BOOK")){
			rentBook(costumer, ýtemId, rentDate);
		}
	}

	public void returnItem(int costumerId, String typeOfItem,int ýtemId, Date returnDate) {
		Costumer costumer = findCostumer(costumers, costumerId);

		if(typeOfItem.equalsIgnoreCase("MOVÝE")){
			returnMovie(costumer, ýtemId, returnDate);
		}		
		else if(typeOfItem.equalsIgnoreCase("BOOK")){
			returnBook(costumer, ýtemId, returnDate);
		}		
	}

	public void changePolicy(Policy policy) {
		this.policy = policy;
	}
	
	@SuppressWarnings("resource")
	private Costumer createCostumer() {
		Scanner scan = new Scanner(System.in);
		Costumer costumer = null;
		System.out.println("What will be the costumer level?\n1.Regular\n2.Silver\n3.Gold\n4.Premium");
		if(scan.hasNextInt()) {
			int x = scan.nextInt();
			if(x==1)
				costumer = new Costumer(costumers.size()+1,Costumer.Level.regular);
			else if(x==2)
				costumer = new Costumer(costumers.size()+1,Costumer.Level.silver);
			else if(x==3)
				costumer = new Costumer(costumers.size()+1,Costumer.Level.gold);
			else if(x==4)
				costumer = new Costumer(costumers.size()+1,Costumer.Level.premium);
		}
		System.out.println("New costumer registered with id " + costumer.getCostumerNum()+ " and level " + costumer.getLevel());
		costumers.add(costumer);
		return costumer;
	}
			
	public void saveMovie(SaveTypes saveType,int movieId) throws ParserConfigurationException, TransformerException, JSONException, IOException {
		int checker =0;
		for(int i =0;i<moviess.size();i++) {
			checker=1;
			if(moviess.get(i).getId()==movieId) {
				moviess.get(i).store(saveType);
			}
		}if(checker==0) {
			System.out.println("There is no such movie that has this id number.");
		}
	}
	
	public void saveBook(SaveTypes saveType,int bookId) throws ParserConfigurationException, TransformerException, JSONException, IOException {
		int checker =0;
		for(int i =0;i<books.size();i++) {
			checker=1;
			if(books.get(i).getId()==bookId) {
				books.get(i).store(saveType);
			}
		}
		if(checker==0) {
			System.out.println("There is no such book that has this id number.");
		}
	}

	public void saveBooks(SaveTypes saveType) throws IOException, JSONException, TransformerException, ParserConfigurationException {
		FileOutput fileOutput = new FileOutput();
		fileOutput.SaveBooks(saveType, books);
	}
	public void saveMovies(SaveTypes saveType) throws IOException, JSONException, TransformerException, ParserConfigurationException {
		FileOutput fileOutput = new FileOutput();
		fileOutput.SaveMovies(saveType, moviess);
	}
	public void saveRentedMoviesAndBooks(SaveTypes saveType) throws ParserConfigurationException, TransformerException, JSONException, IOException{
		FileOutput fileOutput = new FileOutput();
		ArrayList<Movie> rentedMovies = new ArrayList<>();
		ArrayList<Book> rentedbooks = new ArrayList<>();
		for(Movie m : moviess) {
			if(m.isIsrented())
				rentedMovies.add(m);
		}
		for(Book b : books) {
			if(b.isIsrented())
				rentedbooks.add(b);
		}
		fileOutput.SaveMoviesAndBooks(saveType, rentedMovies, rentedbooks);
	}
	
	public String listRentedItems() {
		String st ="";
		for(int i = 0;i<moviess.size();i++) {
			if(moviess.get(i).isIsrented()==true) {
				st += moviess.get(i).toString() + "\n";
			}
		}
		for(int i = 0;i<books.size();i++) {
			if(books.get(i).isIsrented()==true) {
				st += books.get(i).toString() + "\n";
			}
		}
		return st;
	}

	public double totalAmountOfInvocesSpecificDay(Date date) {
		double totalAmount = 0;
		for(Invoce ý : ýnvoces) {
			if(ý.ýnvoceDate.equals(date)) {
				totalAmount+= ý.amount;
			}
		}
		return totalAmount;
	}
	
	@Override
	public String toString() {
		String st = "";
		for(Movie m: moviess ) {
			st += "Movie id: " + m.getId() + " Movie name: " + m.getName() + " Actor: " + m.getActor() + " Producer: " 
					+ m.getProducer() + " Genre: " + m.getGenre()+"\n";
		}
		st+="\n";
		for(Book b: books) {
			st += "Book id: " + b.getId() + " Book name: " + b.getName() + " Author: " + b.getAuthor() 
				+ " Publisher: " + b.getPublisher();
		}
		return st;
	}	
	
	@SuppressWarnings("deprecation")
	private Date addDate(Date date,int x) {
		Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate());
		newDate.setDate(date.getDate()+x);
		return newDate;
	}
	
	private Costumer findCostumer(ArrayList<Costumer> costumers, int costumerId) {
		Costumer costumer = null;
		for(int i =0;i<costumers.size();i++) {
			if(costumers.get(i).getCostumerNum() == costumerId) {
				costumer = costumers.get(i);
			}
		}
		if(costumer == null) {
			System.out.println("There is no such costumer registered.");
			costumer = createCostumer();
			costumers.add(costumer);
		}
		return costumer;
	}
			
	private void rentMovie(Costumer costumer, int ýtemId, Date rentDate) {
		int checker = 0;
		for(int i =0;i<moviess.size();i++) {
			if(moviess.get(i).getId()==ýtemId) {
				checker++;
				if(!moviess.get(i).isIsrented()) {
					if(costumer.getRentedMovie()==null) {
						costumer.setRentedMovie(moviess.get(i));
						moviess.get(i).setRentDate(rentDate);
						moviess.get(i).rentItem();
						double amount = policy.charge-(policy.charge*costumer.getLevel().getDiscount());
						Invoce ýnvoce = new Invoce(this, amount ,costumer, rentDate,"MOVIE");
						ýnvoces.add(ýnvoce);
						ýnvoce.prepareInvoce();
					}else {System.out.println("You have already rent a movie you have to return back first.");}				
				}else {System.out.println("already rented");}
			}
		}
		if(checker==0) {System.out.println("There is no such movie in inventory.");}
	}
	
	private void rentBook(Costumer costumer, int ýtemId, Date rentDate) {
		int checker = 0;
		for(int i =0;i<books.size();i++) {
			if(books.get(i).getId()==ýtemId) {
				checker++;
				if(!books.get(i).isIsrented()) {
					if(costumer.getRentedBook()==null) {
						costumer.setRentedBook(books.get(i));
						books.get(i).setRentDate(rentDate);
						books.get(i).rentItem();
						double amount = policy.charge-(policy.charge*costumer.getLevel().getDiscount());
						Invoce ýnvoce = new Invoce(this, amount, costumer, rentDate,"BOOK");
						ýnvoces.add(ýnvoce);
						ýnvoce.prepareInvoce();
					}else {System.out.println("You have already rent a book you have to return back first.");}		
				}else {System.out.println("already rented");}				
			}
		}
		if(checker==0) {System.out.println("There is no such movie in inventory.");}
	}

	private void returnMovie(Costumer costumer, int ýtemId, Date returnDate) {
		int overdueDate= 0;
		int checker = 0;
		if (costumer.getRentedMovie()!=null){
			for(int i =0;i<moviess.size();i++) {
				if(moviess.get(i).getId()==ýtemId) {
					if(costumer.getRentedMovie().equals(moviess.get(i))) {
						checker++;
						if(moviess.get(i).isIsrented() && addDate(moviess.get(i).getRentDate(), 2).after(returnDate)) {
							moviess.get(i).returnItem();
							costumer.setRentedMovie(null);
						}
						else {
							overdueDate = (int) (returnDate.getTime() - addDate(moviess.get(i).getRentDate(), 2).getTime())/(1000*60*60*24);
							double amount = overdueDate*this.policy.overdueCharge;
							System.out.println("late for: " + overdueDate+ " days. You have to pay for overdue days " + amount);
							ýnvoces.add(new Invoce(this, amount, costumer, returnDate,"Movie"));
						}												
					}
				}
			}
			if(checker==0){System.out.println("There is no such movie in inventory or this costumer is not rent this book.");}
		}else {System.out.println("This costumer has not rented movie.");}
	}
	
	private void returnBook(Costumer costumer, int ýtemId, Date returnDate) {
		int overdueDate= 0;
		int checker = 0;
		if (costumer.getRentedBook()!=null){
			for(int i =0;i<books.size();i++) {
				if(books.get(i).getId()==ýtemId && costumer.getRentedBook().equals(books.get(i)) ) {
					checker++;
					if(books.get(i).isIsrented() && addDate(books.get(i).getRentDate(), 2).after(returnDate)) {
						books.get(i).returnItem();
					}
					else {
						overdueDate = (int) (returnDate.getTime() - addDate(books.get(i).getRentDate(), 2).getTime())/(1000*60*60*24);
						double amount = overdueDate*this.policy.overdueCharge;
						System.out.println("late for: " + overdueDate+ " days. You have to pay for overdue days " + amount );
						ýnvoces.add(new Invoce(this, amount, costumer, returnDate,"Book"));
					}												
				}
			}
			if(checker==0){System.out.println("There is no such book in inventory or this costumer is not rent this book.");}
		}else {System.out.println("This costumer has not rented book.");}
	}
		
	public boolean searchMovies(String genre,String producer,String actor) {
		for(int i=0;i< moviess.size();i++) {
			if(moviess.get(i).getGenre().equalsIgnoreCase(genre) && moviess.get(i).getProducer().equalsIgnoreCase(producer) && moviess.get(i).getActor().equalsIgnoreCase(actor)) {
				System.out.println("There is a movie that name is "+moviess.get(i).getName());
				return moviess.get(i).search();
			}		
		}return false;
	}
	public boolean searchGenreandProducer(String genre,String producer) {
		for(int i=0;i< moviess.size();i++) {
			if(moviess.get(i).getGenre().equals(genre) && moviess.get(i).getProducer().equals(producer)) {
				System.out.println("There is a movie that name is "+moviess.get(i).getName());
				return moviess.get(i).search();
			}		
		}return false;
	}
	public boolean searchGenreandActor(String genre,String actor) {
		for(int i=0;i< moviess.size();i++) {
			if(moviess.get(i).getGenre().equalsIgnoreCase(genre)&& moviess.get(i).getActor().equalsIgnoreCase(actor)) {
				System.out.println("There is a movie that name is "+moviess.get(i).getName());
				return moviess.get(i).search();
			}			
		}return false;
	}
	public boolean searchActorandProducer(String actor,String producer) {
		for(int i=0;i<moviess.size();i++) {
			if(moviess.get(i).getActor().equalsIgnoreCase(actor) && moviess.get(i).getProducer().equalsIgnoreCase(producer)){
				System.out.println("There is a movie that name is "+moviess.get(i).getName());
				return moviess.get(i).search();
			}	
		}return false;
	}
	
	public boolean searchBooks(String author,String name,String publisher) {
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getAuthor().equalsIgnoreCase(author) && books.get(i).getName().equalsIgnoreCase(name) && books.get(i).getPublisher().equalsIgnoreCase(publisher)) {
				System.out.println("There is a book that name is "+books.get(i).getName());
				return books.get(i).search();
			}				
		}return false;
	}
	public boolean searchAuthorandName(String author,String name) {
		for(int i=0;i< books.size();i++) {
			if(books.get(i).getAuthor().equalsIgnoreCase(author) && books.get(i).getName().equalsIgnoreCase(name)){
				System.out.println("There is a book that name is "+books.get(i).getName());
				return books.get(i).search();
			}
				
		}return false;
	}
	public boolean searchAuthorandPublisher(String author,String publisher) {
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getAuthor().equalsIgnoreCase(author) && books.get(i).getPublisher().equalsIgnoreCase(publisher)){
				System.out.println("There is a book that name is "+books.get(i).getName());
				return books.get(i).search();
			}
		}return false;
	}
	public boolean searchNameandPublisher(String name,String publisher) {
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getName().equalsIgnoreCase(name)&& books.get(i).getPublisher().equalsIgnoreCase(publisher)){
				System.out.println("There is a book that name is "+books.get(i).getName());
				return books.get(i).search();
			}	
		}return false;
	}

}

