package Business;
import java.util.Random;

public class BloodTest extends Analysis {

	private int glucose;
	private int cholesterol;
	private Random rand = new Random();
	public BloodTest() {
		super();
		glucose= rand.nextInt(34)+65;
		cholesterol = rand.nextInt(99)+100;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Glucose: " +  glucose + "\nCholesterol: " + cholesterol; 
	}


	public int getGlucose() {
		return glucose;
	}


	public void setGlucose(int glucose) {
		this.glucose = glucose;
	}


	public int getCholesterol() {
		return cholesterol;
	}


	public void setCholesterol(int cholesterol) {
		this.cholesterol = cholesterol;
	}
	
	
}
