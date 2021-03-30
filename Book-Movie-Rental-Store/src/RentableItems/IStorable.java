package RentableItems;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;

import FileOperations.FileOutput;

public interface IStorable {

	void store(FileOutput.SaveTypes saveType) throws ParserConfigurationException, TransformerException, JSONException, IOException;
	
}
