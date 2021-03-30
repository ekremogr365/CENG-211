package RentalStroe;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;

import FileOperations.FileOutput.SaveTypes;
import RentalStroe.BookAndMovieRentalStore.Policy;

public class ViewStoreQueries {

	@SuppressWarnings("resource")
	public void Start() throws IOException, ParserConfigurationException, TransformerException, JSONException {
		ArrayList<Costumer> costumers = new ArrayList<>();
		BookAndMovieRentalStore rentalstore = new BookAndMovieRentalStore(costumers,Policy.NewReleasePolicy);
		while(true) {
		Scanner scan = new Scanner(System.in);
		System.out.println("1.Store opretion\n2.Costumer operation\n3.Exit");	
		if(scan.hasNextInt()) {
			int operation1 = scan.nextInt();
			switch (operation1) {
			case 1:
				System.out.println("1.Change Policy\n2.Search Item\n3.Save Item\n4.Save Items\n5.List total ýnvoces specific day\n6.List rented ýtems");
				if(scan.hasNextInt()){
					int operation2 = scan.nextInt();
					switch (operation2) {
					case 1:
						rentalstore.changePolicy(scanPolicy(rentalstore));
						break;
					case 2:
						System.out.println("1.Movie\n2.Book");
						if(scan.hasNextInt()) {
							int operation3 = scan.nextInt();
							switch (operation3) {
							case 1:
								System.out.println("1.By Genre, Producer and Actor\n2.By Genre and Producer\n3.By Genre and Actor\n4.By Actor and Producer");
								if(scan.hasNextInt()) {
									int operation4 = scan.nextInt();
									switch (operation4) {
									case 1:
										rentalstore.searchMovies(scanString("genre"), scanString("producer"), scanString("actor"));
										break;
									case 2:
										rentalstore.searchGenreandProducer(scanString("genre"), scanString("producer"));
										break;
									case 3:
										rentalstore.searchGenreandActor(scanString("genre"), scanString("actor"));
										break;
									case 4:
										rentalstore.searchActorandProducer(scanString("actor"), scanString("producer"));
										break;
									default:
										break;
									}
								}else {
									continue;
								}
								break;
							case 2:
								System.out.println("1.By Author, Name and Publisher\n2.By Author and Name\n3.By Author and Publisher\n4.By Name and Publisher");
								if(scan.hasNextInt()) {
									int operation4 = scan.nextInt();
									switch (operation4) {
									case 1:
										rentalstore.searchBooks(scanString("author"), scanString("name"), scanString("publisher"));
										break;
									case 2:
										rentalstore.searchAuthorandName(scanString("author"), scanString("name"));
										break;
									case 3:
										rentalstore.searchAuthorandPublisher(scanString("author"), scanString("publisher"));
										break;
									case 4:
										rentalstore.searchNameandPublisher(scanString("name"), scanString("publisher"));
										break;
									default:
										break;
									}
								}
								break;
							default:
								break;
							}
						break;
						}
						break;
					case 3:
						System.out.println("1.Movie\n2.Book");
						if(scan.hasNextInt()) {
							int operation3 = scan.nextInt();
							switch (operation3) {
							case 1:
								rentalstore.saveMovie(scanSaveType(), scanItemId());
								break;
							case 2:
								rentalstore.saveBook(scanSaveType(), scanItemId());
								break;
							default:
								break;
							}
						}
						break;	
					case 4:
						System.out.println("1.Movies\n2.Books");
						if(scan.hasNextInt()) {
							int operation3 = scan.nextInt();
							switch (operation3) {
							case 1:
								rentalstore.saveMovies(scanSaveType());
								break;
							case 2:
								rentalstore.saveBooks(scanSaveType());
								break;
							default:
								break;
							}
						}
						break;
						
					case 5:
						System.out.println(rentalstore.totalAmountOfInvocesSpecificDay(scanDate()));
						break;
					case 6:
						System.out.println(rentalstore.listRentedItems());
						break;			
					default:
						break;
					}
				}
				
				break;
			case 2:
				System.out.println("1.Rent Item\n2.Return Item");
				int operation5;
				if(scan.hasNextInt()) {
					operation5 = scan.nextInt();
					switch (operation5) {
					case 1:
						rentalstore.rent(scanCostumerNumber(), scanTypeOfItem(), scanItemId(), scanDate());
						break;
					case 2:
						rentalstore.returnItem(scanCostumerNumber(), scanTypeOfItem(), scanItemId(), scanDate());
						break;
					default:
						break;
					}
				}
				break;
			case 3:
				System.out.println("Before closing the program do you want to save rented books and movies?(yes/no)");
				if(scanString("your answer").equalsIgnoreCase("yes"))
					rentalstore.saveRentedMoviesAndBooks(scanSaveType());
				System.out.println("Exiting...");
				System.exit(-1);
				break;
			default:
				break;
			}
		}else {
			continue;
		}
		}
	}
	
	@SuppressWarnings("resource")
	private int scanItemId() {
		Scanner scan = new Scanner(System.in);
		int ýd = 0;
		System.out.println("Item ýd: ");
		if(scan.hasNextInt()) {
			ýd = scan.nextInt();
			return ýd;
		}else
			System.out.println("Wrong Input!");
		return scanItemId();
	}
	
	@SuppressWarnings("resource")
	private Policy scanPolicy(BookAndMovieRentalStore rentalstore) {
		Scanner scan = new Scanner(System.in);
		int policy = 0;
		if(rentalstore.policy.equals(Policy.NewReleasePolicy)) {
			System.out.println("What would you like to change the policy New Release Policy to Old Release Policy or Bargain Policy?\n"
					+ "1. Old Release Policy\n2. Bargain Policy");
			if(scan.hasNextInt()){
				policy= scan.nextInt();
				if(policy==1) {
					System.out.println(rentalstore.policy + " change to Old Release Policy");
					return Policy.OldReleasePolicy;
				}
				else if(policy==2) {
					System.out.println(rentalstore.policy + " change to Bargain Policy");
					return Policy.BargainPolicy;
				}else {
					System.out.println("There is no such policy exist.");
					return scanPolicy(rentalstore);
				}
			}else {
				System.out.println("You are enter invalid input");
				return scanPolicy(rentalstore);
			}
		}
		else if(rentalstore.policy.equals(Policy.OldReleasePolicy)) {
			System.out.println("What would you like to change the policy Old Release Policy to New Release Policy or Bargain Policy?\n"
					+ "1. New Release Policy\n2. Bargain Policy");
			if(scan.hasNextInt()){
				policy= scan.nextInt();
				if(policy==1) {
					System.out.println(rentalstore.policy + " change to New Release Policy");
					return Policy.NewReleasePolicy;
				}
				else if(policy==2) {
					System.out.println(rentalstore.policy + " change to Bargain Policy");
					return Policy.BargainPolicy;
				}else {
					System.out.println("There is no such policy exist.");
					return scanPolicy(rentalstore);
				}
				
			}else {
				System.out.println("You are enter invalid input");
				return scanPolicy(rentalstore);
			}
		}else if(rentalstore.policy.equals(Policy.BargainPolicy)) {
			System.out.println("What would you like to change the policy Bargain Policy to New Release Policy or Old Release Policy?\n"
					+ "1. New Release Policy\n2. Old Release Policy");
			
			if(scan.hasNextInt()){
				policy= scan.nextInt();
				if(policy==1) {
					System.out.println(rentalstore.policy + " change to New Release Policy");
					return Policy.NewReleasePolicy;
				}
				else if(policy==2) {
					System.out.println(rentalstore.policy + " change to Old Release Policy");
					return Policy.BargainPolicy;
				}else {
					System.out.println("There is no such policy exist.");
					return scanPolicy(rentalstore);
				}				
			}else {
				System.out.println("You are enter invalid input");
				return scanPolicy(rentalstore);
			}			
		}else
			return null;
	}
	
	@SuppressWarnings({ "deprecation", "resource"})
	private Date scanDate() {
		Scanner scan = new Scanner(System.in);
		Date newdate = new Date(0000, 00, 00);
		Date currentDate = new Date();
		int year,month, date;
		
		try {
		System.out.println("enter year");
		year = scan.nextInt()-1900;
		if(year<-1900 ) {
			System.out.println("Year can to be negative!!! Please enter valid year.");
			return scanDate();
		}
		System.out.println("enter month");
		month = scan.nextInt()-1;
		if(month<0 || month>11) {
			System.out.println("There is no such month!!! Please enter between 1-12.");
			return scanDate();
		}
		System.out.println("enter date");
		date = scan.nextInt();
		if(date<1 || date>31) {
			System.out.println("There is no such date in months!!! Please enter between 1-31.");
			return scanDate();
		}
		}catch (InputMismatchException e) {
			System.out.println("Wrong Ýnput!!!");
			return scanDate();
		}		
		newdate.setDate(date);
		newdate.setMonth(month);
		newdate.setYear(year);
		if(newdate.after(currentDate)||(newdate.getYear()==currentDate.getYear()&&newdate.getMonth()==currentDate.getMonth()&&newdate.getDate()==currentDate.getDate()))
			return newdate;
		else {
			System.out.println("Your date is past! Please write unpassed date.");
			return scanDate();
		}
	}
			
	@SuppressWarnings("resource")
	private String scanTypeOfItem() {
		Scanner scan = new Scanner(System.in);
		System.out.println("type ýtem: ");
		String typeýtem= scan.next();
		if(typeýtem.equalsIgnoreCase("Book")||typeýtem.equalsIgnoreCase("movie"))
			return typeýtem;
		else 
			System.out.println("There is no such ýtem that you can rent or return\nPlease write your type of item again");
			return scanTypeOfItem();
		
		
	}

	@SuppressWarnings("resource")
	private int scanCostumerNumber(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Costumer Number: ");
		if(scan.hasNextInt()) {
			int costumerNumber = scan.nextInt();
			return costumerNumber;
		}else
			return scanCostumerNumber();

	}
	
	@SuppressWarnings("resource")
	private SaveTypes scanSaveType() {
		Scanner scan = new Scanner(System.in);
		String savetype = "";
		System.out.println("Enter save type(XML/JSON): ");
		savetype = scan.nextLine();
		if(savetype.equalsIgnoreCase("xml")) {
			return SaveTypes.XML;
		}
		else if(savetype.equalsIgnoreCase("json")) {
			return SaveTypes.JSON;
		}	
		else {
			System.out.println("You are enter invalid save type! Please enter again");
			return scanSaveType();
		}
			
	}

	@SuppressWarnings("resource")
	private String scanString(String parametre) {
		String st = "";
		Scanner scan = new Scanner(System.in);
		System.out.print("Write "+ parametre + ": ");
		st = scan.nextLine();
		return st;
	}

	
	
}
