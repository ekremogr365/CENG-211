package ConsoleIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import FileIo.FileOperations;
import Musicians.BellPlayer;
import Musicians.Celloist;
import Musicians.Drummer;
import Musicians.Flutist;
import Musicians.Maestro;
import Musicians.Musician;
import Musicians.Violinist;
import Musicians.Violist;

public class Concert {
	
	public void start() throws NumberFormatException, IOException {
	

		ArrayList<String[]> piece1Beats = new ArrayList<>();
		ArrayList<String[]> piece2Beats = new ArrayList<>();
		ArrayList<String[]> piece3Beats = new ArrayList<>();
		ArrayList<String[]> piece4Beats = new ArrayList<>();

		while(true) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			Maestro maestro;		
			System.out.println("Which piece do you want to play?\n1.Piece1\n2.Piece2\n3.Piece3\n4.Piece4\n5.Exit! ");
			if(scan.hasNextInt()){
				int operation = scan.nextInt();		
			switch (operation) {
			case 1:
				maestro = new Maestro(piece1Beats);
				System.out.println("Piece 1 is played " + maestro.determineChangeInTempo());
				piece1Beats = createPiece("piece1.txt");
				System.out.println(play(piece1Beats));
				break;
			case 2:
				maestro = new Maestro(piece2Beats);
				System.out.println("Piece 2 is played " + maestro.determineChangeInTempo());
				piece2Beats = createPiece("piece2.txt");
				System.out.println(play(piece2Beats));
				break;
			case 3:
				maestro = new Maestro(piece3Beats);
				System.out.println("Piece 3 is played " + maestro.determineChangeInTempo());
				piece3Beats = createPiece("piece3.txt");
				System.out.println(play(piece3Beats));
				break;
			case 4:
				maestro = new Maestro(piece4Beats);
				System.out.println("Piece 4 is played " + maestro.determineChangeInTempo());
				piece4Beats = createPiece("piece4.txt");
				System.out.println(play(piece4Beats));
				break;
			case 5:
				System.exit(0);
			default:
				break;
			}
			}else {
				System.out.println("Your input is not integer!!! Please enter again.");
				continue;
			}
	
		}	
	}
		
	public ArrayList<String[]> createPiece(String FileName) throws IOException{//we read file in this method and used string tokenizer than added in piecebeatlist 
		FileOperations fileopr = new FileOperations();
		File piece = fileopr.openFile(FileName);
		BufferedReader pieceReader = fileopr.readFile(piece.getName());
		String line =  "";
		StringTokenizer stTokenizer;
		ArrayList<String[]> PieceBeatList= new ArrayList<String[]>();
		
		while ((line = pieceReader.readLine()) != null) {
			int i = 0;
			stTokenizer = new StringTokenizer(line);
			String[] part = new String[stTokenizer.countTokens()];
			
			while(stTokenizer.hasMoreTokens()) {
				part[i]= stTokenizer.nextToken();
				i++;
			}
			PieceBeatList.add(part);
		}
		
	    return PieceBeatList;
	}
	
	public ArrayList<Musician> createMusicianList(ArrayList<String[]> pieceBeats){//we created musician object with piecebeats in this method than we added in musician list
		ArrayList<Musician> musicianList = new ArrayList<>();
		Musician violinist = new Violinist(pieceBeats);
		Musician violist = new Violist(pieceBeats);
		Musician celloist = new Celloist(pieceBeats);
		Musician flutist = new Flutist(pieceBeats);
		Musician bellPlayer = new BellPlayer(pieceBeats);
		Musician drummer = new Drummer(pieceBeats);
		
		musicianList.add(violinist);
		musicianList.add(violist);
		musicianList.add(celloist);
		musicianList.add(flutist);
		musicianList.add(bellPlayer);
		musicianList.add(drummer);
		
		return musicianList;
	}
		
	public String play(ArrayList<String[]> beatList ) {//In this method asks for user, how type want to see beats. 
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Which type do you wanna see beats?\n1.Musician by musician\n2.Part by part");
		if(scan.hasNextInt()){
			int operation = scan.nextInt();		
		switch (operation) {
		case 1:
			return musicianbyMusician(beatList);
		case 2:
			return partbyPart(beatList);
		default:
			break;
		}		
		}
		return "Wrong input! Choose again.";
	}	
	
	public String musicianbyMusician(ArrayList<String[]> beatList ) {//if user chose first section this method works. 
		String part = "";
		ArrayList<Musician> musicians = createMusicianList(beatList);
		String[] parts;
		for(int i = 0;i<musicians.size();i++) {
			if(!isMusiciansPlays(musicians.get(i))) {//We firstly check for all musicians if plays piece
				part += musicians.get(i).getName() +  " is not played: \n";
			}else {
				part += musicians.get(i).getName() +  " is played: \n";
				parts = musicians.get(i).playPiece().split("\n");
				for(int j =0;j<parts.length;j++) {						//If they playing we added they in part.
					if(!musicians.get(i).playPiece().split("\n")[j].contains("-")){
						part += parts[j] + "\n";	
					}
				}
			}
						
		}
		return part;
	}
	
	public String partbyPart(ArrayList<String[]> beatList) {//if user chose second section this method works.
		ArrayList<Musician> musicians = createMusicianList(beatList);
		ArrayList<String[]> beats = new ArrayList<>();
		String part = "";
		int counter = musicians.get(0).playPiece().split("\n").length;//it counts number of musicians
		for(int i =0;i<musicians.size();i++) {
			beats.add(musicians.get(i).playPiece().split("\n"));			
		}
		for(int i =0;i<counter;i++) {
			for(int j =0;j<beats.size();j++) {
				if(!beats.get(j)[i].contains("-")) //it check the last part of musician with "-" 
					part += (musicians.get(j).getName()  +" is played: \n" + beats.get(j)[i]) + "\n";
			}
		}
		return part;
	}
	
	private boolean isMusiciansPlays(Musician music) {//we used this method in musicianbyMusician method for check all musicians if plays piece 
		int counter =0;
		String[] parts = music.playPiece().split("\n");
		for(int i =0;i<parts.length;i++) {
			if(parts[i].contains("-"))
				counter++;
		}
		return (counter != parts.length);//if the musician do not play in this piece, it returns false, else returns true
	}
	
}