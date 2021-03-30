package Musicians;

import java.util.ArrayList;

public class Maestro {
	
	ArrayList<String[]> beatList;

	public Maestro(ArrayList<String[]> beatList) {
		this.beatList = beatList;
	}
	
	public String determineTempo(String[] part) {//this method determines the tempo of parts	
		String tempo ="";
		double count =0;
		for(int i = 0;i<part.length;i++) {
			count += Double.valueOf(part[i].substring(1));
		}
		if (count<8)
			tempo = "Prestissimo";
		else if(count>=8 && count<16)
			tempo = "Vivace";
		else if(count>=16 && count<18)
			tempo = "Allegretto";
		else if(count>=18 && count<22)
			tempo = "Moderato";
		else if(count>=22 && count<23)
			tempo = "Adagietto";
		else if(count>=23 && count<24)
			tempo = "Andante";
		else if(count>=24 && count<27)
			tempo = "Larghetto";
		else if(count>=27 && count<29)
			tempo = "Lento";
		else if(count>=29 && count<33)
			tempo = "Grave";
		else if(count>=33 && count<37)
			tempo = "Larghissimo";
		return tempo;
	}
	
	public String determineChangeInTempo() {//this method determines the change tempo of pieces.
		String tempo ="";
		double count =0;
		for(int i = 0;i<beatList.size();i++) {
			for(int j = 0;j<beatList.get(i).length;j++) {
				count += Double.valueOf(beatList.get(i)[j].substring(1));//we returned string value of beats from double value
			}		
		}
		if (count<83)
			tempo = "Lentando";
		else if(count>=83 && count<125)
			tempo = "Ritenuto";
		else if(count>=125 && count<132)
			tempo = "Stretto";
		else if(count>=132 && count<152)
			tempo = "Accelerando";
		
		return tempo;

	}	
}
