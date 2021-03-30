package FileOperations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import RentableItems.IStorable;
import RentableItems.Movie;
import RentableItems.Book;

public class FileOutput {
	public enum SaveTypes{XML,JSON}
	public SaveTypes saveType;

	
	public void SaveObject(SaveTypes saveType,IStorable ýtem) throws ParserConfigurationException, TransformerException, JSONException, IOException {
		switch (saveType) {
		case XML:
			saveXml(ýtem);
			break;
		case JSON:
			saveJason(ýtem);
			break;

		default:
			break;
		}
	}
	
	public void SaveBooks(SaveTypes saveType, ArrayList<Book> books) throws IOException, JSONException, TransformerException, ParserConfigurationException {
		switch (saveType) {
		case XML:
			savexmlBooks(books);
			break;
		case JSON:
			saveJasonBooks(books);
			break;

		default:
			break;
		}
		
	}
	
	public void SaveMovies(SaveTypes saveType, ArrayList<Movie> movies) throws IOException, JSONException, TransformerException, ParserConfigurationException {
		switch (saveType) {
		case XML:
			saveXmlMovies(movies);
			break;
		case JSON:
			saveJsonMovies(movies);
			break;

		default:
			break;
		}		
	}
	
	
	public void SaveMoviesAndBooks(SaveTypes saveType,ArrayList<Movie> movies,ArrayList<Book> books) throws ParserConfigurationException, TransformerException, JSONException, IOException {
		switch (saveType) {
		case XML:
			SaveRentedMoviesAndBooksXML(movies, books);
			break;
		case JSON:
			SaveRentedMoviesAndBooksJASON(movies, books);

		default:
			break;
		}		
	}
	
	private void SaveRentedMoviesAndBooksJASON(ArrayList<Movie> movies,ArrayList<Book> books) throws JSONException, IOException {
		FileWriter jsonWriter = new FileWriter("Rented MoviesJSON.json");
		FileWriter jsonWriter2 = new FileWriter("Rented BooksJSON.json");
		for(int i = 0;i<movies.size();i++) {
			JSONObject movieObj = new JSONObject();
			
			movieObj.put("Movie number", movies.get(i).getId());
			movieObj.put("Name", movies.get(i).getName());
			movieObj.put("Genre", movies.get(i).getGenre());
			movieObj.put("Producer", movies.get(i).getProducer());
			movieObj.put("Actor", movies.get(i).getActor());
			jsonWriter.write(movieObj.toString());
		}		
		for(int i = 0;i<books.size();i++) {
			JSONObject bookObj = new JSONObject();
			
			bookObj.put("Book number", books.get(i).getId());
			bookObj.put("Name", books.get(i).getName());
			bookObj.put("Author", books.get(i).getAuthor());
			bookObj.put("Publisher", books.get(i).getPublisher());
			jsonWriter2.write(bookObj.toString());
		}
		
		jsonWriter.close();
		jsonWriter2.close();
	}
	
	private void SaveRentedMoviesAndBooksXML(ArrayList<Movie> movies,ArrayList<Book> books) throws ParserConfigurationException, TransformerException {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		// root elements
		Document doc = docBuilder.newDocument();
		Document doc2 = docBuilder.newDocument();

		Element rootElement = doc.createElement("Movies");
		doc.appendChild(rootElement);
		
		Element rootElement2 = doc2.createElement("Books");
		doc2.appendChild(rootElement2);
		
		for(int i = 0;i<movies.size();i++) {
			// staff elements
			Element staff = doc.createElement("Movie");
			rootElement.appendChild(staff);
			
			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(movies.get(i).getId()));
			staff.setAttributeNode(attr);
			
			// firstname elements
			Element movieName = doc.createElement("MovieName");
			movieName.appendChild(doc.createTextNode(movies.get(i).getName()));
			staff.appendChild(movieName);
			
			// lastname elements
			Element MovieGenre = doc.createElement("MovieGenre");
			MovieGenre.appendChild(doc.createTextNode(movies.get(i).getGenre()));
			staff.appendChild(MovieGenre);
			
			// lastname elements
			Element Producer = doc.createElement("Producer");
			Producer.appendChild(doc.createTextNode(movies.get(i).getProducer()));
			staff.appendChild(Producer);
			
			// lastname elements
			Element Actor = doc.createElement("Actor");
			Actor.appendChild(doc.createTextNode(movies.get(i).getActor()));
			staff.appendChild(Actor);					
		}
		
		
		for(Book book : books) {
			// staff elements
			Element staff = doc2.createElement("Book");
			rootElement2.appendChild(staff);
			
			// set attribute to staff element
			Attr attr = doc2.createAttribute("id");
			attr.setValue(String.valueOf(book.getId()));
			staff.setAttributeNode(attr);
			
			// firstname elements
			Element movieName = doc2.createElement("BookName");
			movieName.appendChild(doc2.createTextNode(book.getName()));
			staff.appendChild(movieName);
			
			// lastname elements
			Element MovieGenre = doc2.createElement("Author");
			MovieGenre.appendChild(doc2.createTextNode(book.getAuthor()));
			staff.appendChild(MovieGenre);
			
			// lastname elements
			Element Producer = doc2.createElement("Publisher");
			Producer.appendChild(doc2.createTextNode(book.getPublisher()));
			staff.appendChild(Producer);

			
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		DOMSource source2 = new DOMSource(doc2);
		StreamResult result = new StreamResult(new File("Rented MoviesXML.xml"));
		StreamResult result2 = new StreamResult(new File("Rented BooksXML.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source2, result2);


	}
	
	private void saveJason(IStorable ýtem) throws JSONException, IOException {
		if(ýtem.getClass().equals(Movie.class)) {
			JSONObject movieObj = new JSONObject();
			Movie movie = (Movie) ýtem;
			movieObj.put("Movie number", movie.getId());
			movieObj.put("Name", movie.getName());
			movieObj.put("Genre", movie.getGenre());
			movieObj.put("Producer", movie.getProducer());
			movieObj.put("Actor", movie.getActor());
			
			
			FileWriter jsonWriter = new FileWriter("movie-"+movie.getName()+"JSON.json");
			jsonWriter.write(movieObj.toString());
			jsonWriter.close();
		}
		if(ýtem.getClass().equals(Book.class)) {
			
			JSONObject bookObj = new JSONObject();
			Book book = (Book) ýtem;
			bookObj.put("Book number", book.getId());
			bookObj.put("Name", book.getName());
			bookObj.put("Author", book.getAuthor());
			bookObj.put("Publisher",book.getPublisher());
			FileWriter jsonWriter = new FileWriter("Book-"+book.getName()+"JSON.json");
			jsonWriter.write(bookObj.toString());
			jsonWriter.close();

			
		}
		
	}
		
	private void saveJsonMovies(ArrayList<Movie> movies) throws IOException, JSONException {
		FileWriter jsonWriter = new FileWriter("MoviesJSON.json");
		for(int i = 0;i<movies.size();i++) {
			JSONObject movieObj = new JSONObject();
			
			movieObj.put("Movie number", movies.get(i).getId());
			movieObj.put("Name", movies.get(i).getName());
			movieObj.put("Genre", movies.get(i).getGenre());
			movieObj.put("Producer", movies.get(i).getProducer());
			movieObj.put("Actor", movies.get(i).getActor());
			jsonWriter.write(movieObj.toString());
		}
		jsonWriter.close();
	}

	private void saveJasonBooks(ArrayList<Book> books) throws JSONException, IOException{
		FileWriter jsonWriter = new FileWriter("BooksJSON.json");
		for(int i = 0;i<books.size();i++) {
			JSONObject bookObj = new JSONObject();
			
			bookObj.put("Book number", books.get(i).getId());
			bookObj.put("Name", books.get(i).getName());
			bookObj.put("Author", books.get(i).getAuthor());
			bookObj.put("Publisher", books.get(i).getPublisher());
			jsonWriter.write(bookObj.toString());
		}
		jsonWriter.close();
	}
	
		
	private Document createXMLItem(IStorable ýtem) throws ParserConfigurationException {
		System.out.println(ýtem.getClass());
		if(ýtem.getClass().equals(Movie.class)) {
			Movie movie = (Movie) ýtem;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("Movie");
			doc.appendChild(rootElement);
			
			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(movie.getId()));
			rootElement.setAttributeNode(attr);
			
			// firstname elements
			Element movieName = doc.createElement("MovieName");
			movieName.appendChild(doc.createTextNode(movie.getName()));
			rootElement.appendChild(movieName);
			
			// lastname elements
			Element MovieGenre = doc.createElement("MovieGenre");
			MovieGenre.appendChild(doc.createTextNode(movie.getGenre()));
			rootElement.appendChild(MovieGenre);
			
			// lastname elements
			Element Producer = doc.createElement("Producer");
			Producer.appendChild(doc.createTextNode(movie.getProducer()));
			rootElement.appendChild(Producer);
			
			// lastname elements
			Element Actor = doc.createElement("Actor");
			Actor.appendChild(doc.createTextNode(movie.getActor()));
			rootElement.appendChild(Actor);		
		
			return doc;
		}
		if(ýtem.getClass().equals(Book.class)) {
			Book book = (Book) ýtem;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("Book");
			doc.appendChild(rootElement);
			
			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(book.getId()));
			rootElement.setAttributeNode(attr);
			
			// firstname elements
			Element movieName = doc.createElement("BookName");
			movieName.appendChild(doc.createTextNode(book.getName()));
			rootElement.appendChild(movieName);
			
			// lastname elements
			Element MovieGenre = doc.createElement("Author");
			MovieGenre.appendChild(doc.createTextNode(book.getAuthor()));
			rootElement.appendChild(MovieGenre);
			
			// lastname elements
			Element Producer = doc.createElement("Publisher");
			Producer.appendChild(doc.createTextNode(book.getPublisher()));
			rootElement.appendChild(Producer);
			
			return doc;
		}
		return null;
	}
	
	private void saveXml(IStorable ýtem) throws ParserConfigurationException, TransformerException {
		if(ýtem.getClass().equals(Movie.class)) {
			Movie movie = (Movie) ýtem;
			Document doc = createXMLItem(ýtem);
		
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Movie-" + movie.getName()+"-XML.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);
		}
		else if(ýtem.getClass().equals(Book.class)) {
			Book book = (Book) ýtem;			
			Document doc = createXMLItem(ýtem);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Book-" + book.getId()+"XML.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);
		}

	}

	private void saveXmlMovies(ArrayList<Movie> movies) throws ParserConfigurationException, TransformerException {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Movies");
		doc.appendChild(rootElement);
		for(int i = 0;i<movies.size();i++) {
			// staff elements
			Element staff = doc.createElement("Movie");
			rootElement.appendChild(staff);
			
			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(movies.get(i).getId()));
			staff.setAttributeNode(attr);
			
			// firstname elements
			Element movieName = doc.createElement("MovieName");
			movieName.appendChild(doc.createTextNode(movies.get(i).getName()));
			staff.appendChild(movieName);
			
			// lastname elements
			Element MovieGenre = doc.createElement("MovieGenre");
			MovieGenre.appendChild(doc.createTextNode(movies.get(i).getGenre()));
			staff.appendChild(MovieGenre);
			
			// lastname elements
			Element Producer = doc.createElement("Producer");
			Producer.appendChild(doc.createTextNode(movies.get(i).getProducer()));
			staff.appendChild(Producer);
			
			// lastname elements
			Element Actor = doc.createElement("Actor");
			Actor.appendChild(doc.createTextNode(movies.get(i).getActor()));
			staff.appendChild(Actor);		
			
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("MoviesXML.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		
	}
	
	private void savexmlBooks(ArrayList<Book> books) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Books");
		doc.appendChild(rootElement);
		for(Book book : books) {
			// staff elements
			Element staff = doc.createElement("Book");
			rootElement.appendChild(staff);
			
			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(book.getId()));
			staff.setAttributeNode(attr);
			
			// firstname elements
			Element movieName = doc.createElement("BookName");
			movieName.appendChild(doc.createTextNode(book.getName()));
			staff.appendChild(movieName);
			
			// lastname elements
			Element MovieGenre = doc.createElement("Author");
			MovieGenre.appendChild(doc.createTextNode(book.getAuthor()));
			staff.appendChild(MovieGenre);
			
			// lastname elements
			Element Producer = doc.createElement("Publisher");
			Producer.appendChild(doc.createTextNode(book.getPublisher()));
			staff.appendChild(Producer);

			
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("BooksXML.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
	}

	

}
