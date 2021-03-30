package Business;
import java.util.Date;

import Exceptions.AnalysisNotFoundException;

public class Examination{

	private Doctor doctor;
	private Patient patient;
	private Analysis[] analysis;
	private Treatment treatment;
	public Examination(Doctor doctor, Patient patient) {
		
		this.doctor = doctor;
		this.patient = patient;
		this.analysis = new Analysis[2];
		this.treatment = null;
	}
	
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	public Analysis[] getAnalysis() {
		return analysis;
	}


	public Analysis getAnalysis1() throws AnalysisNotFoundException {
		long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - analysis[0].getStartTime(); // Geçen süreyi milisaniye cinsinden elde ediyoruz
		double seconds = (double)estimatedTime/1000;
		if(seconds>15) {
			analysis[0].setIsready(true);;
			return analysis[0];
		}
		else 
			throw new AnalysisNotFoundException("Analysis Not Found. Ready in: " + (15-seconds) + "seconds.");
	}
	public Analysis getAnalysis2() throws AnalysisNotFoundException {
		long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - analysis[1].getStartTime(); // Geçen süreyi milisaniye cinsinden elde ediyoruz
		double seconds = (double)estimatedTime/1000;
		if(seconds>15) {
			analysis[1].setIsready(true);;
			return analysis[1];
		}
		else 
			throw new AnalysisNotFoundException("Analysis Not Found: Ready in: " + (15-seconds) + "seconds." );
	}
	public void setAnalysisBloodTest() {
		this.analysis[0] = new BloodTest();
	}
	public void setAnalysisRadiologyTest() {
		this.analysis[1] = new Radiology();
	}
	
	public Treatment getTreatment() {
		return treatment;
	}
	public void setTreatmentTherapy() {
		this.treatment = new Therapy();
	}
	public void setTreatmentSurgery(Date surgeryDate,int numberOfDays) {
		if(doctor.getClass().equals(Surgeon.class))
			this.treatment = new Surgery((Surgeon) doctor,surgeryDate,numberOfDays);
		else
			System.out.println("Not Surgeon");
	}
	
	
	
	
	
	
}
