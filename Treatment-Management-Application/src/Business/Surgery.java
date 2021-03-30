package Business;
import java.util.Date;;

public class Surgery extends Treatment{
	
	
	private Surgeon surgeon;
	private Date surgeryDate;
	private int numberOfDays;
	public Surgery(Surgeon surgeon,Date surgeryDate, int numberOfDays) {
		super();
		this.surgeon=surgeon;
		this.surgeryDate=surgeryDate;
		this.numberOfDays=numberOfDays;
	}
	public Surgeon getSurgeon() {
		return surgeon;
	}
	public void setSurgeon(Surgeon surgeon) {
		this.surgeon = surgeon;
	}
	public Date getSurgeryDate() {
		return surgeryDate;
	}
	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
	

	
	
	
}
