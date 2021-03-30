package RentableItems;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;

import FileOperations.FileOutput;

public class Movie extends RentableItem{
	private final String actor;
	private final String producer;
	private final String genre;
	FileOutput fileoutput;

	public Movie(int id, String name, String actor, String producer, String genre) {
		super(id, name);
		this.actor = actor;
		this.producer = producer;
		this.genre = genre;
		fileoutput=new FileOutput();

	}	
		
	@Override
	public void rentItem() {
		setIsrented(true);
	}
	@Override
	public void returnItem() {
		setIsrented(false);		
	}

	@Override
	public boolean search() {
		return true;	
	}
	
	@Override
	public void store(FileOutput.SaveTypes saveType) throws ParserConfigurationException, TransformerException, JSONException, IOException  {
		fileoutput.SaveObject(saveType, this);		
		
	}


	@Override
	public String toString() {
		
		return "Movie id: " + getId() + " Name: " + getName() + " Actor: " + actor+ " Producer : " + producer + "Genre: " + genre; 
	}

	public String getActor() {
		return actor;
	}

	public String getProducer() {
		return producer;
	}

	public String getGenre() {
		return genre;
	}	
}
