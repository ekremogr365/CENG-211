package Business;
import java.util.ArrayList;

public class Hospital {

	private ArrayList<? super Patient> patients;
	private ArrayList<? super Doctor> doctors;
	private String name;
	public Hospital(String name ) {
		this.name= name;
		patients= new ArrayList<>();
		doctors= new ArrayList<>();
	}
	public ArrayList<? super Patient> getPatients() {
		return patients;
	}
	public ArrayList<? super Doctor> getDoctors() {
		return doctors;
	}
	public String getName() {
		return name;
	}	
	
	
}
