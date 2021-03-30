package Presentation;

import java.util.ArrayList;

import Business.Doctor;
import Business.Examination;
import Business.Hospital;
import Business.Surgeon;

public class HospitalApp {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		Hospital host = new Hospital("XXXXX");
		ArrayList<Examination> examinations = new ArrayList<>();
		host.getDoctors().add(new Doctor("doctorA", 1));
		host.getDoctors().add(new Surgeon("surgeonA", 2));
		host.getDoctors().add(new Surgeon("surgenB", 3));
		host.getDoctors().add(new Doctor("doctorB", 4));
		host.getDoctors().add(new Doctor("doctorC", 5));
		host.getDoctors().add(new Doctor("doctorD", 6));
		host.getDoctors().add(new Surgeon("surgenC", 7));
		host.getDoctors().add(new Surgeon("surgenD", 8));
		
		Console console = new Console(host, examinations);
		console.start();
	}

}
