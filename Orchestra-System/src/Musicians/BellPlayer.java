package Musicians;

import java.util.ArrayList;
import java.util.Iterator;

public class BellPlayer extends PercussionInstrumentMusician {
	
	public BellPlayer(ArrayList<String[]> beatList) {
		super(beatList);
		name = "BellPlayer";
	}

	
	public String playPiece() {
	
		ArrayList<String[]> newBeatList = copyList(setBeatList());
		Iterator<String[]> iterator1 = newBeatList.iterator();		
		int j=0;
		String[] newArray = {"-"};
		Maestro maestro1 = new Maestro(beatList);		
		if(maestro1.determineChangeInTempo().equals("Stretto")) {
			while(iterator1.hasNext()) {
				String[] keeper = iterator1.next();	
				if(keeper[0] != "-") {
					if(maestro1.determineTempo(keeper).equals("Grave")){
						for(int i = 0; i<keeper.length;i++) {
							String compare = keeper[i].substring(0, 1);
							if(compare.compareTo("F")!=0 && compare.compareTo("G")!=0 && compare.compareTo("A")!=0 && compare.compareTo("B")!=0) {
								keeper[i] = "X";
							}
						}
					}else					
						newBeatList.set(j, newArray);	
				}
				j++;
			}
		}else
			for(int i =1;i<3;i++) {
				newBeatList.set(newBeatList.size()-i, newArray);
			}

		return writeParts(newBeatList);
	}
	
	
}
