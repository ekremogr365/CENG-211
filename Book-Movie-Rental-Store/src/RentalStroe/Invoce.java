package RentalStroe;

import java.util.Date;

public class Invoce {
	
	double amount;
	Costumer costumer;
	Date ýnvoceDate;
	String ýtem;
	BookAndMovieRentalStore rentShop;
	
	public Invoce(BookAndMovieRentalStore rentShop, double amount, Costumer costumer, Date rentDate, String item) {
		this.rentShop = rentShop;
		this.amount = amount;
		this.costumer = costumer;
		this.ýnvoceDate = rentDate;
		this.ýtem = item;
	}
	
	public void prepareInvoce() {
		
		if(costumer.getLevel().equals(Costumer.Level.regular)) {
			System.out.println("You have to pay: " + rentShop.policy.charge());
		}
		else if (costumer.getLevel().equals(Costumer.Level.silver)) {
			System.out.println("You have %10 discount. You have to pay: " + (rentShop.policy.charge()-(rentShop.policy.charge()*costumer.getLevel().getDiscount())));
		}
		else if (costumer.getLevel().equals(Costumer.Level.gold)) {
			System.out.println("You have %15 discount. You have to pay: " + (rentShop.policy.charge()-(rentShop.policy.charge()*costumer.getLevel().getDiscount())));
		}
		else if (costumer.getLevel().equals(Costumer.Level.premium)) {
			System.out.println("You have %20 discount. You have to pay: " + (rentShop.policy.charge()-(rentShop.policy.charge()*costumer.getLevel().getDiscount())));
		}
		if(ýtem.equals("MOVIE"))
			System.out.println("you are rented in " + ýnvoceDate + " you must get back in " + addDate(ýnvoceDate, 2));
		else if(ýtem.equals("BOOK"))
			System.out.println("you are rented in " + ýnvoceDate + " you must get back in " + addDate(ýnvoceDate, 7));

	}
	
	@SuppressWarnings("deprecation")
	private Date addDate(Date date,int x) {
		Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate());
		newDate.setDate(date.getDate()+x);
		return newDate;
	}
	
}

