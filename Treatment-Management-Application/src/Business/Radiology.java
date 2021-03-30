package Business;
import java.util.Random;

public class Radiology extends Analysis {

	private boolean isthereProblem;
	private Random rand = new Random();

	public Radiology() {
		super();
		isthereProblem = rand.nextBoolean();
	}

	@Override
	public String toString() {
		return "IsthereProblem " + isthereProblem;
	}

	public boolean isIsthereProblem() {
		return isthereProblem;
	}

	public void setIsthereProblem(boolean isthereProblem) {
		this.isthereProblem = isthereProblem;
	}
	
	
	
	
}
