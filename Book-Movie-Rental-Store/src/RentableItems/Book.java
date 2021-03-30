package RentableItems;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;

import FileOperations.FileOutput;

public class Book extends RentableItem{

	private final String author;
	private String publisher;
	FileOutput fileoutput;
	public Book(int id, String name, String author, String publisher) {
		super(id, name);
		this.author = author;
		this.publisher = publisher;
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
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void store(FileOutput.SaveTypes saveType) throws ParserConfigurationException, TransformerException, JSONException, IOException  {
		fileoutput.SaveObject(saveType, this);		
		
	}
	
	@Override
	public String toString() {
		return "Book id: " + getId() + " Name: " + getName() + " Author: " + author+ " Publisher: " + publisher; 
 	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}
}
