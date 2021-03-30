package Business;
import java.util.ArrayList;
import java.util.Date;

import Exceptions.AnalysisNotFoundException;
import Exceptions.PatientNotFoundException;

public class Doctor {
	
	private int id;
	private String name;
	public Doctor(String name, int id) {
		this.name=name;
		this.id= id;
	}
	
	
	public void askBlododTestAnalysis(Examination examination) {
		examination.setAnalysisBloodTest();			
	}
	
	public void askRadiologyTestAnalysis(Examination examination) {
		examination.setAnalysisRadiologyTest();			
	}
	
	public void seeBloodTestResults(Examination examination) throws AnalysisNotFoundException {
		if(examination.getAnalysis1().isIsready()==true) {
			BloodTest bl = (BloodTest) examination.getAnalysis1();
			System.out.println(bl.toString());
		}
		else
			throw new AnalysisNotFoundException("Not ready");
	}

	public void seeRadiologyTestResults(Examination examination) throws AnalysisNotFoundException {
		if(examination.getAnalysis2().isIsready()==true) {
			Radiology ra = (Radiology) examination.getAnalysis2();
			System.out.println(ra.toString());
		}
		else
			throw new AnalysisNotFoundException("Not ready");
	}
	
	public void decideSurgery(Examination examination,boolean isSurgery,Date surgeryDate,int numberOfDays) {
		if(getClass().equals(Surgeon.class) ) {
			if(isSurgery==true) {
				examination.setTreatmentSurgery(surgeryDate,numberOfDays);
			}
		}
	}
	
	public void writePrescription(Examination examination,String prescription) {
		examination.getPatient().setPrescription(prescription);
	}

	public String NotExaminedPatients(ArrayList<Examination> examinations) {
		String patients ="";
		for(Examination ex: examinations) {
			if(ex.getDoctor().equals(this)&&ex.getPatient().isExamined==false) {
				patients+= ex.getPatient().toString() + "\n";
			}
		}
		return patients;
	}
	
	public String ExaminedPatients(ArrayList<Examination> examinations) {
		String patients ="";
		for(Examination ex: examinations) {
			if(ex.getDoctor().equals(this)&&ex.getPatient().isExamined==true) {
				patients+= ex.getPatient().toString() + "\n";
			}
		}
		return patients;
	}
	
	public String ExaminedPatientsAll(ArrayList<Examination> examinations) {
		String patients ="";
		for(Examination ex: examinations) {
			if(ex.getPatient().isExamined==true) {
				patients+= ex.getPatient().toString() + "\n";
			}
		}
		return patients;
	}
	
	public boolean searchPatient(int id,ArrayList<Examination> examinations) throws PatientNotFoundException {
		for(Examination ex: examinations) {
			if(ex.getPatient().getId()==id&&ex.getPatient().isExamined==true) {
				return true;
			}
		}
		throw new PatientNotFoundException("Patient not found.") ;
	}
	
	
	@Override
	public boolean equals(Object otherObject) {
		if(otherObject==null) {return false;}
		else if(getClass()!=otherObject.getClass()) {return false;}
		else {
			Doctor otherDoctor = (Doctor) otherObject;			
			return id==otherDoctor.id && name.equals(otherDoctor.name);
		}
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
