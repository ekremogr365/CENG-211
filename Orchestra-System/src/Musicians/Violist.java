package Musicians;

import java.util.ArrayList;
import java.util.Iterator;

public class Violist extends StringInstrumentMusician{

	public Violist(ArrayList<String[]> beatList) {
		super(beatList);
		name = "Violist";
	}

	@Override
	public String playPiece() {
		ArrayList<String[]> newBeatList = copyList(beatList);
		Iterator<String[]> newIterator = newBeatList.iterator();
		String[] newArray = {"-"};
		int i =1;
		Maestro maestro = new Maestro(newBeatList);
		if(maestro.determineChangeInTempo().equals("Ritenuto")) {	
			newIterator.next();
			while(newIterator.hasNext()) {
				newIterator.next();
				newBeatList.set(i, newArray);
				i++;
			}
			return writeParts(newBeatList);
		}else			
			return super.playPiece();
	}

}

