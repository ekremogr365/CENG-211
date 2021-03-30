package RentalStroe;

import RentableItems.Book;
import RentableItems.Movie;

public class Costumer {
	
	public enum Level {
	    regular(0.0),
	    silver(0.1),
	    gold(0.15),
	    premium(0.20);

	    private double discount;

	    private Level(double discount) {
	        this.discount = discount;
	    }

	    public double getDiscount() {
	        return discount;
	    }
	}
	private final int costumerNum;
	private Movie rentedMovie;
	private Book rentedBook;
	private Level level;
	public Costumer(int costumerNum, Level level ) {
		this.costumerNum = costumerNum;
		this.level = level;
		rentedMovie = null;
		rentedBook = null;
		
	}
	public Movie getRentedMovie() {
		return rentedMovie;
	}
	public void setRentedMovie(Movie rentedMovie) {
		this.rentedMovie = rentedMovie;
	}
	public Book getRentedBook() {
		return rentedBook;
	}
	public void setRentedBook(Book rentedBook) {
		this.rentedBook = rentedBook;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public int getCostumerNum() {
		return costumerNum;
	}	
}
