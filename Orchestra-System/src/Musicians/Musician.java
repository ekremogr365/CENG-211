package Musicians;

import java.util.ArrayList;
import java.util.Iterator;

public class Musician {
	
	ArrayList<String[]> beatList;
	Iterator<String[]> iterator;
	String name;
	public Musician(ArrayList<String[]> beatList) {
		this.beatList = beatList;
		this.iterator = beatList.iterator();
		this.name = "Musician";
	}
	
	public String playPiece() {
		
		ArrayList<String[]> newBeatList = copyList(beatList);
		return writeParts(newBeatList);
	}
		
	public String writeParts(ArrayList<String[]> beatlist) {
		
		String part = "";
		Maestro maestro = new Maestro(this.beatList);		
		Iterator<String[]> newIterator = beatlist.iterator();
		int i =0;
		while(newIterator.hasNext()) {
			String[] keeper = newIterator.next();	
				part += "Part " + (i+1) + ": ";
				for(int j = 0;j<keeper.length;j++) {
					part +=  keeper[j].substring(0, 1) + " " ;
				}
				part += maestro.determineTempo(beatList.get(i)) + "\n" ;

			i++;
		}
		return part;
	}
		
	public ArrayList<String[]> copyList(ArrayList<String[]> beatlist){
	
		ArrayList<String[]> copylist = new ArrayList<String[]>();	
		Iterator<String[]> newIterator = beatlist.iterator();

		while(newIterator.hasNext()){
			String[] keeper = newIterator.next();	
			String[] copyString = new String[keeper.length];
			for(int j =0;j<keeper.length;j++) {
				copyString[j] = keeper[j]; 
			}
			copylist.add(copyString);
		}
		return copylist;		
	}
	
	public ArrayList<String[]> setBeatList(){
		return beatList;		
	}

	public String getName() {
		return name;
	}

	
	
}
