import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BillQuery {

	private Apartment apt;
	
	public BillQuery(Apartment apt) {
		
		this.apt = apt;
	}
			
	public int totalAmountOf_UnpaidBills() {
		int total_amaount = 0; 
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {				
					if(apt.getApartment()[i][j].getListof_Bills().get(k).isPayment_Ýnfo() == false) {
						total_amaount += apt.getApartment()[i][j].getListof_Bills().get(k).getAmount();
					}
				}		
			}	
		}
		return total_amaount;
	}
	
	public int unpaid_Bills_certain_type() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String type;
		System.out.println("Which tpye bill do you want to calculate?(electric/water/cleaning/heating)");
		type = scan.nextLine();
		type = type.toLowerCase();
		int total_amaount = 0; 
		while(true) {
			if(type.equals("electric") || type.equals("water") || type.equals("cleaning") || type.equals("heating")){
				for(int i=0; i< apt.getApartment().length; i++) {
					for(int j =0; j<apt.getApartment()[0].length; j++) {
						for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
							if(apt.getApartment()[i][j].getListof_Bills().get(k).isPayment_Ýnfo() == false && apt.getApartment()[i][j].getListof_Bills().get(k).getType().equals(type)) {
								total_amaount += apt.getApartment()[i][j].getListof_Bills().get(k).getAmount();
							}
						}		
					}
					
				}
				return total_amaount;
			}else {
				System.out.println("There is no such bill type!!!");
				return unpaid_Bills_certain_type();
			}
		
		}
	}
	
	public int unpaid_Bills_certain_floor() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int floor = 0;
		System.out.println("Which floor do you want to calculate");
		try {
			floor = scan.nextInt();
			if(floor > apt.getMax_floor_Num() || floor<=0) {
				System.out.println("You are entering wrong floor number!!! Please enter between this numbers: " + "1-" + apt.getMax_floor_Num());
				return unpaid_Bills_certain_floor();
			}
		}catch(InputMismatchException e){
			System.out.println("Wrong Ýnput!!! Please write valid number.");
			return unpaid_Bills_certain_floor();
		}
		int total_amaount = 0; 
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
					if(apt.getApartment()[i][j].getListof_Bills().get(k).isPayment_Ýnfo() == false && apt.getApartment()[i][j].getFloor_Number() ==(floor)) {
						total_amaount += apt.getApartment()[i][j].getListof_Bills().get(k).getAmount();
					}
				}		
			}	
		}
		return total_amaount;
	}
	
	
	@SuppressWarnings("deprecation")
	public String listOf_unpaidBills() {
		
		String list = "";
		list += "Flat Id | Bill Id | Bill Type | Remainig Time \n";
		int remaining_date;
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {				
					if(apt.getApartment()[i][j].getListof_Bills().get(k).isPayment_Ýnfo() == false) {
						remaining_date = apt.getApartment()[i][j].getListof_Bills().get(k).getPayment_Deadline_date().getDate()-current_Date().getDate()
								+ 30*(apt.getApartment()[i][j].getListof_Bills().get(k).getPayment_Deadline_date().getMonth()-current_Date().getMonth()
										+ 365*(apt.getApartment()[i][j].getListof_Bills().get(k).getPayment_Deadline_date().getYear()-current_Date().getYear()));
						
						if(remaining_date<=0) {
							if(apt.getApartment()[i][j].getListof_Bills().get(k).getId()>9) {
								list +=   "   " + apt.getApartment()[i][j].getListof_Bills().get(k).getFlat_Id() + "    |   " 
										+ apt.getApartment()[i][j].getListof_Bills().get(k).getId()+ "    |   " 
												+ apt.getApartment()[i][j].getListof_Bills().get(k).getType().substring(0, 4) + ".   | " + " Passed deadline!! \n";
							}else {
								list +=   "   " + apt.getApartment()[i][j].getListof_Bills().get(k).getFlat_Id() + "    |   " 
										+ apt.getApartment()[i][j].getListof_Bills().get(k).getId()+ "     |   " 
												+ apt.getApartment()[i][j].getListof_Bills().get(k).getType().substring(0, 4) + ".   | " + " Passed deadline!! \n";
							}
							
						}else {
							if(apt.getApartment()[i][j].getListof_Bills().get(k).getId()>9) {
								list +=  "   " + apt.getApartment()[i][j].getListof_Bills().get(k).getFlat_Id() +  "    |   " 
										+ apt.getApartment()[i][j].getListof_Bills().get(k).getId()+ "    |   " 
													+ apt.getApartment()[i][j].getListof_Bills().get(k).getType().substring(0, 4) + ".   |  " + remaining_date+"\n";
							}else {
								list +=  "   " + apt.getApartment()[i][j].getListof_Bills().get(k).getFlat_Id() +  "    |   " 
										+ apt.getApartment()[i][j].getListof_Bills().get(k).getId()+ "     |   " 
													+ apt.getApartment()[i][j].getListof_Bills().get(k).getType().substring(0, 4) + ".   |  " + remaining_date+"\n";
							}														
						}

					}
				}		
			}	
		}
		return list;
	}
	
	@SuppressWarnings("deprecation")
	public int before_CertainDate_Paid() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		Date certain_date = new Date(0000, 00, 00);
		int year,month, date;
		int total_amaount = 0; 
		try {
		System.out.println("enter year");
		year = scan.nextInt()-1900;
		if(year<-1782 ) {
			System.out.println("Year can to be negative!!! Please enter valid year.");
			return before_CertainDate_Paid();
		}
		System.out.println("enter month");
		month = scan.nextInt()-1;
		if(month<0 || month>11) {
			System.out.println("There is no such month!!! Please enter between 1-12.");
			return before_CertainDate_Paid();
		}
		System.out.println("enter date");
		date = scan.nextInt();
		if(date<1 || date>31) {
			System.out.println("There is no such date in months!!! Please enter between 1-31.");
			return before_CertainDate_Paid();
		}
		}catch (InputMismatchException e) {
			System.out.println("Wrong Ýnput");
			return before_CertainDate_Paid();
		}		
		certain_date.setDate(date);
		certain_date.setMonth(month);
		certain_date.setYear(year);
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
					if(apt.getApartment()[i][j].getListof_Bills().get(k).isPayment_Ýnfo() == true &&apt.getApartment()[i][j].getListof_Bills().get(k).getLast_Update_date().before(certain_date)) {
						total_amaount += apt.getApartment()[i][j].getListof_Bills().get(k).getAmount();
						System.out.println(apt.getApartment()[i][j].getListof_Bills().get(k));						
					}
				}		
			}	
		}
		return total_amaount;
	}
				
	public String passedDeadline_certainType() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String type;
		System.out.println("Which tpye bill do you want to calculate?(electric/water/cleaning/heating)");
		type = scan.nextLine();
		type = type.toLowerCase();
		int total_amaount = 0; 
		int counter = 0;
		while(true) {
			if(type.equals("electric") || type.equals("water") || type.equals("cleaning") || type.equals("heating")){
				for(int i=0; i< apt.getApartment().length; i++) {
					for(int j =0; j<apt.getApartment()[0].length; j++) {
						for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
							if(apt.getApartment()[i][j].getListof_Bills().get(k).isPayment_Ýnfo() == false && apt.getApartment()[i][j].getListof_Bills().get(k).getType().equals(type)
									&& apt.getApartment()[i][j].getListof_Bills().get(k).getPayment_Deadline_date().before(current_Date())) {
								total_amaount += apt.getApartment()[i][j].getListof_Bills().get(k).getAmount();
								counter++;
							}
						}		
					}
					
				}
				return "Total amount: " + total_amaount + ", Number of unpaid bills that passed deadline: " + counter;
			}else {
				System.out.println("There is no such bill type!!!");
				return passedDeadline_certainType();
			}
		}
	}
	
	
	public int averageAmount_rooms() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int room_Num;
		System.out.println("Which number of rooms do you want to calculate");
		
		int total_amaount = 0; 
		int average_counter=0;
		
		try {
			room_Num = scan.nextInt();						
			
		}catch(InputMismatchException e){
			System.out.println("Wrong Ýnput!!! Please write valid number.");
			return averageAmount_rooms();
		}				
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				
				for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
					if(apt.getApartment()[i][j].getNumberof_Rooms() ==room_Num) {
						total_amaount += apt.getApartment()[i][j].getListof_Bills().get(k).getAmount();						
					}
				}		
			}	
		}
		if(total_amaount>0) {
			for(int i=0; i< apt.getApartment().length; i++) {
				for(int j =0; j<apt.getApartment()[0].length; j++) {
					if(apt.getApartment()[i][j].getNumberof_Rooms() ==room_Num) {
						average_counter++;
					}
				}	
			}
			return total_amaount/average_counter;	
		}else {
			System.out.println("No such flat that has this much room");
			return averageAmount_rooms();
		}			
	}
	
	public int averageAmount_squaremeter() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int squareMeter;
		System.out.println("Which square meter do you want to calculate");
		int total_amaount = 0; 
		int average_counter=0;		
		try {
			squareMeter = scan.nextInt();									
		}catch(InputMismatchException e){
			System.out.println("Wrong Ýnput!!! Please write valid number.");
			return	averageAmount_squaremeter();
		}		
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
					if(apt.getApartment()[i][j].getSquare_Meter() > squareMeter ) {
						total_amaount += apt.getApartment()[i][j].getListof_Bills().get(k).getAmount();
					}
				}		
			}	
		}		
		if(total_amaount>0 && squareMeter>0) {
			for(int i=0; i< apt.getApartment().length; i++) {
				for(int j =0; j<apt.getApartment()[0].length; j++) {
					if(squareMeter > 0 && apt.getApartment()[i][j].getSquare_Meter() >squareMeter) {
						average_counter++;
					}
				}	
			}
			return total_amaount/average_counter;	
		}else {
			System.out.println("No such flat that has this much large or your input is smaller than 0");
			return averageAmount_squaremeter();
		}	
	}
	
	public String list_Flats() {
		
		String list ="";
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				list += apt.getApartment()[i][j].toString() +"\n";
				
			}
		}
		
		return list;
	}
	
	public String list_Bills() {
		String list ="";
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				for(int k =0; k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
					list += apt.getApartment()[i][j].getListof_Bills().get(k).toString() +"\n";
				}
			}
		}
		
		return list;
	}
	
	
	@SuppressWarnings("resource")
	public String changing_PaymentInfo() throws FileNotFoundException {
		
		String newName = "HW1-BillingInfo-" + Sdate(current_Date())+".csv";
		PrintWriter pw = new PrintWriter(new File(newName));
		StringBuilder sb = new StringBuilder();
		Scanner scan = new Scanner(System.in);

		int billId = 0;
		String line = "";
		System.out.println("Enter bill id that you want to change it's payment info ");
		try {
			billId = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Wrong input!");
			return changing_PaymentInfo();
		}
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
					if(apt.getApartment()[i][j].getListof_Bills().get(k).getId() == billId ){
						System.out.println(apt.getApartment()[i][j].getListof_Bills().get(k).getId());
						if(apt.getApartment()[i][j].getListof_Bills().get(k).isPayment_Ýnfo()) {
							apt.getApartment()[i][j].getListof_Bills().get(k).setPayment_Ýnfo(false);
						}else {
							apt.getApartment()[i][j].getListof_Bills().get(k).setPayment_Ýnfo(true);
						}		
					}
				}
			}
		}
		
		for(int i=0; i< apt.getApartment().length; i++) {
			for(int j =0; j<apt.getApartment()[0].length; j++) {
				for(int k = 0;k<apt.getApartment()[i][j].getListof_Bills().size();k++) {
					line = apt.getApartment()[i][j].getListof_Bills().get(k).getId() + "," + apt.getApartment()[i][j].getListof_Bills().get(k).getFlat_Id() 
							+  "," + apt.getApartment()[i][j].getListof_Bills().get(k).getAmount() + "," + apt.getApartment()[i][j].getListof_Bills().get(k).getType()
							+ ","+ apt.getApartment()[i][j].getListof_Bills().get(k).isPayment_Ýnfo() + "," 
							+ Sdate(apt.getApartment()[i][j].getListof_Bills().get(k).getPayment_Deadline_date()) 
							+ ","+ Sdate(current_Date()) ;
					sb.append(line);
					sb.append("\n");
				}
			}
		}
		pw.write(sb.toString());
        pw.close();
        return newName;
	}
	
	
	public Date getDate(String date) {		    
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
	    try {
			date1=formatter.parse(date);
			return date1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return date1;		
	}

	public Date current_Date() {
		Date dateobj = new Date(); 
		return dateobj;		
	}

	@SuppressWarnings("deprecation")
	public String Sdate(Date dateobj) {
		String date = "";
		date += (dateobj.getYear() +1900)+"-";
		if(dateobj.getMonth()<9) {
			date+="0" + (dateobj.getMonth()+1)+"-";
		}else {
			date += (dateobj.getMonth()+1)+"-";
		}
		if(dateobj.getDate()<10) {
			date += "0" + dateobj.getDate();
		}else {
			date += dateobj.getDate();
		}
		return date;
	}
		
	public Apartment getApt() {
		return apt;
	}
	public void setApt(Apartment apt) {
		this.apt = apt;
	}
	
	
}
