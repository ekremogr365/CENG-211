package IztechMap;

import java.io.IOException;

import FileOperations.FileInput;
import GarphOperations.Graph;

public class IztechMapApp {
	
	public static void main(String[] args) throws IOException {
		IztechMap map = new IztechMap(new FileInput("iztech.izmap").createHasMap(),
				new Graph(new FileInput("iztech.izmap").createHasMap()).mapToGraph());
		ConsoleIO view = new ConsoleIO(map);
		view.Start();
	}
	
	
	
}
