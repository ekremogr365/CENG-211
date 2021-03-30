package Presentation;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import Business.Analysis;
import Business.BloodTest;
import Business.Doctor;
import Business.Emergency;
import Business.Examination;
import Business.Hospital;
import Business.Inmate;
import Business.Patient;
import Business.Radiology;
import Business.Surgeon;
import Business.Surgery;
import Business.WalkingCase;
import Exceptions.AnalysisNotFoundException;
import Exceptions.ExaminationNotFoundException;
import Exceptions.PatientNotFoundException;

public class Console {

	Hospital host;
	ArrayList<Examination> examinations;
	public Console(Hospital host, ArrayList<Examination> examinations) {
		this.host=host;
		this.examinations=examinations;
	}
	
	
	
	@SuppressWarnings("resource")
	public void start(){

		
			
		while(true) {
				
				Scanner scan = new Scanner(System.in);
				System.out.println("*****************");
				System.out.println("1.Resepsionist Account \n2.Doctor Account\n3.Exit Program");
				
				if(scan.hasNextInt()) {
					int operationx = scan.nextInt();
					switch (operationx) {
					case 1:
						boolean contýnee = true;
						while(contýnee) {
							try {
							Scanner scann = new Scanner(System.in);
							System.out.println("1.Add Patient \n2Finish");
							int operation = scann.nextInt();
							switch (operation) {
							case 1:
								Patient p = scanPatient();
								if(p.getClass().equals(Emergency.class)) {
									System.out.println("Which doctor do you want to see:");
									examinations.add(indexOfLastEmergency(),new Examination(getDoctor(), p));
								}
								else if (p.getClass().equals(WalkingCase.class)){
									System.out.println("Which doctor do you want to see:");
									examinations.add(new Examination(getDoctor(), p));
								}
									
								host.getPatients().add(p);
								break;
							case 2:
								contýnee=false;
								break;
							default:
								
								break;
							}
							}catch (InputMismatchException e) {
								System.out.println("----Wrong Input----");
							}
						}
						break;
					case 2:

						System.out.println("select your account:");
						Doctor dr = getDoctor();
						boolean contýn = true;
						if(dr.getClass().equals(Surgeon.class)) {
							printSurgeryPatientsShouldVisit((Surgeon)dr);
						}
						while(contýn) {
							try {
							Scanner scannn = new Scanner(System.in);
							System.out.println("*****************");
							System.out.println("1.See patients you will see today\n2.SearchPatient\n3.Examination\n4.SearchSurgery"
									+ "\n5.Examined Patients For you\n6.Examined Patients\n7.Close your account.");

								int operation = scannn.nextInt();
								switch (operation) {
								case 1:	
									System.out.println(dr.NotExaminedPatients(examinations));
									break;
								case 2:
									if(dr.searchPatient(scanýd(), examinations))
										System.out.println("This patient examined in the past.");
									break;
								case 3:
									boolean contin = true;

									Examination e =nextExamination(dr);
									while(contin) {
										System.out.println("*****************");
										System.out.println("1.Ask Test\n2.Choose tratment\n3.SeeBlodResult\n4.SeeRadiResult\n5.Write prescription\n  and move next patient");
											int operation1 = scan.nextInt();
											switch (operation1) {
											case 1:
												Analysis analy = choseAn();
												if(analy.getClass().equals(BloodTest.class)) {
													System.out.println("Test is ready in 15 seconds.");
													dr.askBlododTestAnalysis(e);
												}
												else {
													System.out.println("Test is ready in 15 seconds.");
													dr.askRadiologyTestAnalysis(e);
												}
												break;
											case 2:
												if(choseTre().equalsIgnoreCase("Surgery")) {
													if(e.getDoctor().getClass().equals(Surgeon.class)) {
														System.out.println("Surgery Date: ");
														Date surgeryDate = scanDate();
														System.out.println("How many days patient will stay: ");
														int days = scan.nextInt();
														e.setTreatmentSurgery(surgeryDate,days);									
													}else {
														e.setDoctor(chooseSurgeon());
														System.out.println("Surgery Date: ");
														Date surgeryDate = scanDate();
														System.out.println("How many days patient will stay: ");
														int days = scan.nextInt();
														e.setTreatmentSurgery(surgeryDate,days);
													}
													Date discargedDate = new Date(((Surgery)e.getTreatment()).getSurgeryDate().getTime() + (((Surgery)e.getTreatment()).getNumberOfDays() * 1000 * 60 * 60 * 24));
													System.out.println("Day that the patient will be discharged(): ");
													System.out.println(discargedDate);
													Inmate patient = new Inmate(e.getPatient().getId(), e.getPatient().getName(), discargedDate,e.getPatient().getPrescription());
													e.setPatient(patient);
												}	
												else
													e.setTreatmentTherapy();	
												break;
											case 3:
												if(e.getAnalysis()[0]==null) 
													throw new AnalysisNotFoundException("Analysis Not Found"); 
												
												dr.seeBloodTestResults(e);
												break;
											case 4:
												if(e.getAnalysis()[1]==null)
													throw new AnalysisNotFoundException("Analysis Not Found"); 
												dr.seeRadiologyTestResults(e);
												break;
											case 5:
												e.getPatient().setExamined(true);
												System.out.print("Prescription:");
												dr.writePrescription(e, scan.next());
												contin=false;
												break;
											default:
												
												break;
											}
											
																			
									}
									break;
								case 4:
									if(dr.getClass().equals(Surgeon.class)) {
										if(((Surgeon)dr).searchSurgery(scanýd(), examinations)) {
											System.out.println("You have this surgery appointment.");
										}else
											System.out.println("There is no such surgery appointment for you.");
										break;
									}	
									else {
										System.out.println("You are not a surgeon you can not use this option.");
										break;
									}
		
								case 5:
									System.out.println(dr.ExaminedPatients(examinations));
									break;
								case 6:
									System.out.println(dr.ExaminedPatientsAll(examinations));
									break;
								case 7:
									contýn = false;
									break;
								default:
									System.out.println("No such option!!!!");
									break;
								}
													
						}catch (AnalysisNotFoundException e) {
							System.out.println(e.getMessage());
						}catch (PatientNotFoundException e) {
							System.out.println(e.getMessage());
						}catch (ExaminationNotFoundException e) {
							System.out.println(e.getMessage());
						}catch (InputMismatchException e) {
							System.out.println("----Wrong Input.---");
						}
						}
						break;
					
					case 3:
						System.exit(0);
					default:
						
						break;				
					}
				}
				
			}
	}	
	
	public void printSurgeryPatientsShouldVisit(Surgeon surgeon) {
		System.out.println("You should visit patients: ");
		for(Examination ex: examinations) {
			if(ex.getDoctor().equals(surgeon)&&ex.getTreatment()!=null&&ex.getTreatment().getClass().equals(Surgery.class)
					&&((Inmate)ex.getPatient()).getTillStay().after(new Date())) {
				System.out.println(ex.getPatient().toString());				
			}
		}
	}
	
	public Examination nextExamination(Doctor dr) throws ExaminationNotFoundException {
		for(Examination ex: examinations) {
				if(ex.getDoctor().equals(dr)&& ex.getPatient().isExamined()==false) {
					System.out.println(ex.getPatient().toString());
					return ex;
				}
		}
		
		throw new ExaminationNotFoundException("Examination Not Found.");
	}
	
	

	public int indexOfLastEmergency() {
		int index =0;
		for(Examination ex: examinations) {
			if(ex.getPatient().getClass().equals(Emergency.class))
				index++;
		}

		return index;
	}
	

	
	public Patient scanPatient(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int id=-1;
		System.out.print("Patient id: ");
		if(scan.hasNextInt()) {
			id = scan.nextInt();
		}else {
			System.out.println("Wrong input.");
			return scanPatient();
		}
		System.out.print("Name: ");	
		String name = scan.next();
		if(!checkPatientId(id)) {
			System.out.println("1.Emergency\n2.WalkingCase");
			int a = scan.nextInt();
			if(a==1) {
				Patient patient = new Emergency(id, name,"");
				return patient;
			}
			else if(a==2) {
				Patient patient = new WalkingCase(id, name,"");
				return patient;
			}else {
				System.out.println("----Wrong number----");
				return scanPatient();
			}
		}else {
			System.out.println("This ýd is already exist. Try again.");
			return scanPatient();
		}
	}
		
	private boolean checkPatientId(int id) {
		for(Object p: host.getPatients()) {
			if(((Patient) p).getId()==id)
				return true;
		}
		return false;
	}
	
	@SuppressWarnings("resource")
	private int scanýd() {
		Scanner scan = new Scanner(System.in);
		int ýd = 0;
		System.out.println("ýd: ");
		if(scan.hasNextInt()) {
			ýd = scan.nextInt();
			return ýd;
		}else
			System.out.println("Wrong Input!");
		return scanýd();
	}
	
	@SuppressWarnings("resource")
	public Doctor getDoctor() {
		Scanner scan = new Scanner(System.in);
		int id=-1;
		for(Object d: host.getDoctors() ) {
			System.out.println(((Doctor) d).getId() + " " + ((Doctor)d).getName());
		}
		if(scan.hasNextInt())
			id = scan.nextInt();
		else {
			System.out.println("----Wrong Input----");
			return getDoctor();
		}
			
		for(Object d: host.getDoctors() ) {
			if (id==((Doctor) d).getId())
				return (Doctor) d;
		}
		System.out.println("----Wrong ýd----");
		return getDoctor();
	}
	
	@SuppressWarnings("resource")
	public Analysis choseAn() {		
		Scanner scan = new Scanner(System.in);
		System.out.println("1.blod/2.radi");
		int a = scan.nextInt();
		if(a==1) {
			return new BloodTest();
		}
		else
			return new Radiology();
	}
	
	@SuppressWarnings("resource")
	public String choseTre() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1.Surg/2.Trapy");
		int a = scan.nextInt();
		if(a==1) {
			return "Surgery";
		}
		else {
			return "Therapy";
		}
	}
	
	@SuppressWarnings("resource")
	public Surgeon chooseSurgeon() {
		Scanner scan = new Scanner(System.in);
		for(Object surgeoun: host.getDoctors()) {
			if(surgeoun.getClass().equals(Surgeon.class)) {
				System.out.println(((Surgeon) surgeoun).getId() + " " + ((Surgeon)surgeoun).getName());
			}
		}
		int id = scan.nextInt();
		for(Object surgeoun: host.getDoctors() ) {
		if(surgeoun.getClass().equals(Surgeon.class)) {
			if (id==((Surgeon) surgeoun).getId())
				return (Surgeon) surgeoun;
			}
		}	
		System.out.println("----Wrong ýd---");
		return chooseSurgeon();
	}
	
	@SuppressWarnings({ "deprecation", "resource"})
	
	
	private Date scanDate() {
		Scanner scan = new Scanner(System.in);
		Date newdate = new Date(0000, 00, 00);
		Date currentDate = new Date();
		int year,month, date;
		
		try {
		System.out.println("enter year");
		year = scan.nextInt()-1900;
		if(year<-1900 ) {
			System.out.println("Year can to be negative!!! Please enter valid year.");
			return scanDate();
		}
		System.out.println("enter month");
		month = scan.nextInt()-1;
		if(month<0 || month>11) {
			System.out.println("There is no such month!!! Please enter between 1-12.");
			return scanDate();
		}
		System.out.println("enter date");
		date = scan.nextInt();
		if(date<1 || date>31) {
			System.out.println("There is no such date in months!!! Please enter between 1-31.");
			return scanDate();
		}
		}catch (InputMismatchException e) {
			System.out.println("Wrong Ýnput!!!");
			return scanDate();
		}		
		newdate.setDate(date);
		newdate.setMonth(month);
		newdate.setYear(year);
		if(newdate.after(currentDate)||(newdate.getYear()==currentDate.getYear()&&newdate.getMonth()==currentDate.getMonth()&&newdate.getDate()==currentDate.getDate()))
			return newdate;
		else {
			System.out.println("Your date is past! Please write unpassed date.");
			return scanDate();
		}
	}
}
