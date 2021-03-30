package Business;
import java.util.Date;

public class Inmate extends Patient {
	
	private Date tillStay;
	public Inmate(int id,String name, Date tillStay,String prescription) {
		super(id,name,prescription);
		this.tillStay= tillStay;
	}
	public Date getTillStay() {
		return tillStay;
	}
	public void setTillStay(Date tillStay) {
		this.tillStay = tillStay;
	}
	
	
	
}
