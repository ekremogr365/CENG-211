package Musicians;

import java.util.ArrayList;
import java.util.Iterator;

public class Drummer extends PercussionInstrumentMusician {

	public Drummer(ArrayList<String[]> beatList) {
		super(beatList);
		name = "Drummer";
	}

	@Override
	public String playPiece() {

		ArrayList<String[]> newBeatList = copyList(setBeatList());
		Iterator<String[]> iterator1 = newBeatList.iterator();
		int j=0;
		String[] newArray = {"-"};
		Maestro maestro = new Maestro(newBeatList);		
		while(iterator1.hasNext()) {
			String[] keeper = iterator1.next();	
			if(keeper[0] != "-") {	//its for not empty strings		
				if(maestro.determineTempo(keeper).equals("Prestissimo") || maestro.determineTempo(keeper).equals("Vivace") 
						|| maestro.determineTempo(keeper).equals("Allegretto")) {
					for(int i = 0; i<keeper.length;i++) {
						String compare = keeper[i].substring(0, 1);
						if(compare.compareTo("C")!=0 && compare.compareTo("D")!=0 && compare.compareTo("E")!=0) {
							keeper[i] = "X";
						}						
					}
				}else					
					newBeatList.set(j, newArray);				
			}
			j++;
		}
		return writeParts(newBeatList);
	}	
		
}

