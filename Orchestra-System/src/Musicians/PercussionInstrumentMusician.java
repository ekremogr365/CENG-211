package Musicians;

import java.util.ArrayList;

public class PercussionInstrumentMusician extends Musician{
	
	public PercussionInstrumentMusician(ArrayList<String[]> beatList) {
		super(beatList);
		name = "PercussionInstrumentMusician";
	}

	@Override
	public String playPiece() {
		
		ArrayList<String[]> newBeatList = setBeatList();
		return writeParts(newBeatList);
	}
	
	public ArrayList<String[]> setBeatList(){
		String[] emptyArray = {"-"};
		ArrayList<String[]> copyList = new ArrayList<String[]>();
		for(int i = 0; i<beatList.size();i++) {
			if(i<beatList.size()-2) {//because percussion ýnstrument musician do not play without last two parts we added empty array this parts
				copyList.add(emptyArray);
			}else
				copyList.add(beatList.get(i));
		}
		return copyList;
	}
	

}

