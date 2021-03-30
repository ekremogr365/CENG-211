package RentableItems;

import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;

import FileOperations.FileOutput;

public abstract class RentableItem implements ISearchable, IRentable, IStorable{

	private final int id;
	private final String name;
	private Date rentDate;
	private boolean isrented;
	
	@SuppressWarnings("deprecation")
	public RentableItem(int id, String name) {
		this.id = id;
		this.name = name;
		this.rentDate = new Date(0000, 00, 00);
		isrented = false;
	}

	@Override
	public abstract boolean search();

	@Override
	public abstract void rentItem();

	@Override
	public abstract void returnItem();
	
	@Override
	public abstract void store(FileOutput.SaveTypes saveType) throws ParserConfigurationException, TransformerException, JSONException, IOException;

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate2) {
		this.rentDate = rentDate2;
	}

	public boolean isIsrented() {
		return isrented;
	}

	public void setIsrented(boolean isrented) {
		this.isrented = isrented;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
