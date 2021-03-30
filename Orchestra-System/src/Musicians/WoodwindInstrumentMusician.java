package Musicians;

import java.util.ArrayList;

public class WoodwindInstrumentMusician extends Musician{

	public WoodwindInstrumentMusician(ArrayList<String[]> beatList) {
		super(beatList);
		name = "WoodwindInstrumentMusician";
	}

	@Override
	public String playPiece() {
		ArrayList<String[]> newBeatList = setBeatList();			
		return writeParts(newBeatList);
	}
	
	public boolean compareArrays(String[] x1, String[] x2) {
		int counter=0;
		if(x1.length==x2.length) {
			for(int i = 0;i<x1.length;i++) {
				if(x1[i].equals(x2[i]))
					counter++;
			}
			return x1.length == counter;
		}else
			return false;		
	}

	public ArrayList<String[]> setBeatList(){
		
		ArrayList<Integer> counts = new ArrayList<>();
		ArrayList<String[]> newList = new ArrayList<>();
		String[] emptyArray = {"-"};
		for(int i = 0;i<beatList.size();i++) {			
			ArrayList<String[]> compareList = copyList(beatList);
			compareList.remove(i);
			for(int j = 0;j<compareList.size();j++) {
				if(compareArrays(beatList.get(i), compareList.get(j))) {
					counts.add(i);
				}				
			}			
		}
		
		for(int i =0;i<beatList.size();i++) {
			if(counts.contains(i)) {
				newList.add(beatList.get(i));
			}else {
				newList.add(emptyArray);
			}
		}		
		return newList;		
	}
		
		
}
