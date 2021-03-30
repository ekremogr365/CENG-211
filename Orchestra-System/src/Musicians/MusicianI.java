package Musicians;

import java.util.ArrayList;

public interface MusicianI {
	public String playPiece();
	public String writeParts(ArrayList<String[]> beatlist);
	public ArrayList<String[]> copyList(ArrayList<String[]> beatlist);
	

}
