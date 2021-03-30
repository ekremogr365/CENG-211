package Musicians;

import java.util.ArrayList;
import java.util.Iterator;

public class Violinist extends StringInstrumentMusician {

	public Violinist(ArrayList<String[]> beatList) {
		super(beatList);
		name ="Violinist";
	}

	@Override
	public String playPiece() {
		
		ArrayList<String[]> newBeatList = copyList(beatList);
		Iterator<String[]> iterator1 = newBeatList.iterator();
		String[] newArray = {"-"};
		int i = 0;
		Maestro maestro = new Maestro(newBeatList);
		while(iterator1.hasNext()) {
			String[] keeper = iterator1.next();
			if(maestro.determineTempo(keeper).equals("Prestissimo")) {
				newBeatList.set(i, newArray);
			}
			i++;
		}
		return writeParts(newBeatList);
	}
		
}

