import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ApartmantBillingManager {

	public void start(String firstFile, String secondFile) {
		
		

		try {
			BufferedReader br00 = new BufferedReader(new FileReader(firstFile));
			BufferedReader br01 = new BufferedReader(new FileReader(secondFile));

			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			String line =   "";
			String split = ",";
			Apartment apartment0 = new Apartment(3, 2);
			BillQuery query = new BillQuery(apartment0);
			while ((line = br00.readLine()) != null) {
				int i = 0;
				int j = 0;
				ArrayList<Bill> list=new ArrayList<Bill>();
				
			    // use comma as separator
			    String[] info = line.split(split);
			    Flat fl = new Flat(Integer.valueOf(info[0]), Integer.valueOf(info[1]), 
			    		Integer.valueOf(info[2]), Integer.valueOf(info[3]), Integer.valueOf(info[4]), list);
			    
			    i = Integer.valueOf(info[1])-1;
			    j = Integer.valueOf(info[2])-1;
			    apartment0.add(fl, i, j);
			    		
			}
			while ((line = br01.readLine()) != null) {
					
				int i = 0;
				int j = 0;
			    // use comma as separator
			    String[] info = line.split(split);	
			    Bill bill = new Bill(Integer.valueOf(info[0]), Integer.valueOf(info[1]), 
			    		Integer.valueOf(info[2]), info[3], Boolean.parseBoolean(info[4]), query.getDate(info[5]), query.getDate(info[6]));
			    
			    i = (bill.getFlat_Id()-1)/2;
			    j = (bill.getFlat_Id()-1)%2;
			    
			    apartment0.getApartment()[i][j].getListof_Bills().add(bill);
				   
		}
			while(true) {
				System.out.println("Enter your opration: \n1.totalAmountOf_UnpaidBills\n2.unpaid_Bills_certain_type\n"
						+ "3.unpaid_Bills_certain_floor\n4.listOf_unpaidBills\n5.before_CertainDate_Paid\n6.passedDeadline_certainType\n"
						+ "7.averageAmount_rooms\n8.averageAmount_squaremeter\n9.changing_PaymentInfo\n10.list_Flats\n11list_Bills\n12.Exit");
		
				int operation = scan.nextInt();
				String a;
				switch(operation) {
				case  1: 
					System.out.println(query.totalAmountOf_UnpaidBills());
					break;
				case 2:
					System.out.println(query.unpaid_Bills_certain_type());
					break;
				case 3:
					System.out.println(query.unpaid_Bills_certain_floor());
					break;
				case 4:
					System.out.println(query.listOf_unpaidBills());
					break;				
				case 5:
					System.out.println(query.before_CertainDate_Paid());
					break;
				case 6:
					System.out.println(query.passedDeadline_certainType());
					break;
				case 7:
					System.out.println(query.averageAmount_rooms());					
					break;
				case 8:
					System.out.println(query.averageAmount_squaremeter());
					break;
				case 9:
					a = query.changing_PaymentInfo();
					start(firstFile, a);
					break;
				case 10:
					System.out.println(query.list_Flats());
					break;
				case 11:
					System.out.println(query.list_Bills());
					break;
				case 12:
					br00.close();
					br01.close();
					System.exit(0);
				default:
					System.out.println("Wrong number entered!!! Please enter valid number.(1/2/3/4/5/6/7) ");
			}			
		}
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
