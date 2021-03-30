package Business;

public abstract class Patient {
	
	private int id;
	private String name;
	private String prescription;
	boolean isExamined;
	public Patient(int id,String name,String prescription) {
		this.id=id;
		this.name = name;
		this.prescription = prescription;
		this.isExamined = false;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public boolean isExamined() {
		return isExamined;
	}
	public void setExamined(boolean isExamined) {
		this.isExamined = isExamined;
	}
	
	
	
}
