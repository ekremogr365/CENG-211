package FileIo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileOperations {	
	
	public File openFile(String fileName ) throws FileNotFoundException{
		File newFile = new File(fileName);
		return newFile;
	}
	
	public BufferedReader readFile(String fileName){//takes filename String and creates new buffered reader for this file		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return br;		
	}

}
