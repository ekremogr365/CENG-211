package RentalStroe;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;

public class BookAndMovieRentalStoreApp {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, JSONException {
		// TODO Auto-generated method stub
		ViewStoreQueries view = new ViewStoreQueries();
		view.Start();
		
	}

}
