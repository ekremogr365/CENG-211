package FileOperations;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import BusinessNode.BuildingNode;
import BusinessNode.LandscapeNode;
import BusinessNode.Node;
import BusinessNode.NodeIdComperator;

public class FileOutput {
	
	private String fileName;
	public FileOutput(String fileName) {
		this.fileName= fileName;
	}
	
	@SuppressWarnings("unchecked")
	public void overWriteFile(HashMap< ? super Node,ArrayList<? super Node>> �zt) throws IOException{
		HashMap< ? super Node,ArrayList<? super Node>> map = new FileInput(fileName).createHasMap();
		ArrayList<Node> keylist�zt = (ArrayList<Node>) addKeySetToList(�zt);
		ArrayList<Node> keyistmap = (ArrayList<Node>) addKeySetToList(map);
		NodeIdComperator comp = new NodeIdComperator();
		Collections.sort(keylist�zt, comp);
		Collections.sort(keyistmap, comp);
		ArrayList<String> connections= new ArrayList<>(); 
		ArrayList<String> nodes = new FileInput(fileName).extractNodeLines();
		for(int i =0;i<keylist�zt.size();i++) {
			if(!keyistmap.contains(keylist�zt.get(i))) {				
				if(keylist�zt.get(i).getCategory().equals(Node.Category.Building)) {
					BuildingNode a = (BuildingNode) keylist�zt.get(i);
					nodes.add(a.getId() + " [" + a.getCategory() + ", " + a.getType() + ", " + a.getName() + "]");
				}
				else if(keylist�zt.get(i).getCategory().equals(Node.Category.Landscape)) {
					LandscapeNode a = (LandscapeNode) keylist�zt.get(i);
					nodes.add(a.getId() + " [" + a.getCategory() + ", " + a.getType() + ", " + a.getName() + "]");
				}	
				for(int j =0;j<�zt.get(keylist�zt.get(i)).size();j++) {
					connections.add(keylist�zt.get(i).getId() + " <--> " + ((Node)�zt.get(keylist�zt.get(i)).get(j)).getId());
				}				
			}
		}
		for(int i=0;i<keyistmap.size();i++) {
			if(!keylist�zt.contains(keyistmap.get(i))) {
				nodes.remove(keyistmap.get(i).toString());
			}
		}
		for(int i =0;i<keylist�zt.size();i++) {
			for(int j =0;j<�zt.get(keylist�zt.get(i)).size();j++) {
				connections.add(keylist�zt.get(i).getId() + " <--> " + ((Node)�zt.get(keylist�zt.get(i)).get(j)).getId());
			}
		}
		
		Set<String> conn = new HashSet<>(connections);
		connections.clear();
		connections.addAll(conn);
		for(int i =0;i<connections.size();i++) {
			for(int j =0;j<connections.size();j++) {
				String[] connection = connections.get(i).split(" ");
				String[] connection2 = connections.get(j).split(" ");
				if(connection[0].equals(connection2[2])&&connection[2].equals(connection2[0]))
					connections.remove(j);
			}
		}
		
		
		
		ArrayList<String> filess= new ArrayList<>();
		filess.add("# Node (Location) definitions");
		filess.addAll(nodes);
		filess.add("");
		filess.add("# Edges From/To <--> To/From");
		filess.add("# Remember that every arrow (<-->) is bi-directional.");
		filess.addAll(connections);
		
		File oldfile = new File(fileName);
		oldfile.delete();
		File file = new File(fileName);
		BufferedWriter bf = new BufferedWriter(new FileWriter(file));
		for(int i =0;i<filess.size();i++) {
			bf.write(filess.get(i));
			bf.newLine();			
		}
		
		bf.close();		
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<? super Node > addKeySetToList(HashMap< ? super Node,ArrayList<? super Node>> �zt){
		ArrayList<Node> keyList = new ArrayList<>();
		Set<? super Node> keySet = �zt.keySet();
		Iterator<Object> �terator = (Iterator<Object>) keySet.iterator();
		while(�terator.hasNext()) {
			keyList.add((Node)�terator.next());
		}
		NodeIdComperator comp = new NodeIdComperator();
		Collections.sort(keyList, comp);
		return keyList;
	}
}
