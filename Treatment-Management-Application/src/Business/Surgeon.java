package Business;
import java.util.ArrayList;

public class Surgeon extends Doctor{

	public Surgeon(String name,int id){
		super(name,id);
	}
	
	public boolean searchSurgery(int id,ArrayList<Examination> examinations) {
		for(Examination ex: examinations) {
			if(ex.getDoctor().equals(this)&&ex.getTreatment()!=null&&ex.getTreatment().getClass().equals(Surgery.class)) {
				return true;
			}
		}
		return false;
	}
}
