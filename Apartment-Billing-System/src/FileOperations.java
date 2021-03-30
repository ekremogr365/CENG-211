import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileOperations {

	public BufferedReader readFile(String location) {
		
		BufferedReader br = null;
		String line = "";
	    String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(location));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
		
	}
	
	
}
