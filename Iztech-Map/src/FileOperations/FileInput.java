package FileOperations;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import BusinessNode.BuildingNode;
import BusinessNode.LandscapeNode;
import BusinessNode.Node;

public class FileInput {
	
	private String fileName;
	public FileInput(String fileName) {
		this.fileName= fileName;
	}
	
	public HashMap< ? super Node,ArrayList<? super Node>> createHasMap() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line ="";
		HashMap< ? super Node,ArrayList<? super Node>> map = new HashMap<>();
		while((line=br.readLine())!=null) {
			String[] linee = line.split(" ");
			if(!linee[0].equals("#")&&!linee[0].equals("")) {
				if(linee[1].contains("Building")) {
					if(linee[2].contains("Department")) {
						String[] lineee= line.split(", |]");
						map.put( new BuildingNode(Integer.valueOf(linee[0]), Node.Category.Building,BuildingNode.Type.Department , lineee[2]), new ArrayList<>());
					}
					else if(linee[2].contains("Facility")) {
						String[] lineee= line.split(", |]");
						map.put( new BuildingNode(Integer.valueOf(linee[0]), Node.Category.Building,BuildingNode.Type.Facility , lineee[2]), new ArrayList<>());
					}
					else if(linee[2].contains("Administrative")) {
						String[] lineee= line.split(", |]");
						map.put(new BuildingNode(Integer.valueOf(linee[0]), Node.Category.Building,BuildingNode.Type.Administrative , lineee[2]), new ArrayList<>());
					}
					else if(linee[2].contains("Cafeteria")) {
						String[] lineee= line.split(", |]");
						map.put(new BuildingNode(Integer.valueOf(linee[0]), Node.Category.Building,BuildingNode.Type.Cafeteria , lineee[2]), new ArrayList<>());
					}
				}
				else if(linee[1].contains("Landscape")) {
					if(linee[2].contains("Historical")) {
						String[] lineee= line.split(", |]");
						map.put(new LandscapeNode(Integer.valueOf(linee[0]), Node.Category.Landscape,LandscapeNode.Type.HistoricalRuin , lineee[2]), new ArrayList<>());
					}
					else if(linee[2].contains("Waterfall")) {
						String[] lineee= line.split(", |]");
						map.put(new LandscapeNode(Integer.valueOf(linee[0]), Node.Category.Landscape,LandscapeNode.Type.Waterfall , lineee[2]), new ArrayList<>());
					}
					else if(linee[2].contains("Beach")) {
						String[] lineee= line.split(", |]");
						map.put(new LandscapeNode(Integer.valueOf(linee[0]), Node.Category.Landscape,LandscapeNode.Type.Beach , lineee[2]), new ArrayList<>());
					}
				}								
				else if(line.contains("<-->")) {
					map.get(findNode(map, Integer.valueOf(linee[0]))).add(findNode(map, Integer.valueOf(linee[2])));
					map.get(findNode(map, Integer.valueOf(linee[2]))).add(findNode(map, Integer.valueOf(linee[0])));
				
					if(findNode(map, Integer.valueOf(linee[0])).getCategory().equals(Node.Category.Building)&& findNode(map, Integer.valueOf(linee[2])).getCategory().equals(Node.Category.Building)){
						((BuildingNode) findNode(map, Integer.valueOf(linee[0]))).setEdge(((BuildingNode)findNode(map, Integer.valueOf(linee[2]))));
						((BuildingNode) findNode(map, Integer.valueOf(linee[2]))).setEdge(((BuildingNode)findNode(map, Integer.valueOf(linee[0]))));
					}
					else if(((Node) findNode(map, Integer.valueOf(linee[0]))).getCategory().equals(Node.Category.Building)&& findNode(map, Integer.valueOf(linee[2])).getCategory().equals(Node.Category.Landscape)){
						((BuildingNode) findNode(map, Integer.valueOf(linee[0]))).setEdge(((LandscapeNode)findNode(map, Integer.valueOf(linee[2]))));
						((LandscapeNode) findNode(map, Integer.valueOf(linee[2]))).setEdge(((BuildingNode)findNode(map, Integer.valueOf(linee[0]))));
					}
					else if(((Node) findNode(map, Integer.valueOf(linee[0]))).getCategory().equals(Node.Category.Landscape)&& findNode(map, Integer.valueOf(linee[2])).getCategory().equals(Node.Category.Building)){
						((LandscapeNode) findNode(map, Integer.valueOf(linee[0]))).setEdge(((BuildingNode)findNode(map, Integer.valueOf(linee[2]))));
						((BuildingNode) findNode(map, Integer.valueOf(linee[2]))).setEdge(((LandscapeNode)findNode(map, Integer.valueOf(linee[0]))));
					}
					else if(((Node) findNode(map, Integer.valueOf(linee[0]))).getCategory().equals(Node.Category.Landscape)&& findNode(map, Integer.valueOf(linee[2])).getCategory().equals(Node.Category.Landscape)){
						((LandscapeNode) findNode(map, Integer.valueOf(linee[0]))).setEdge(((LandscapeNode)findNode(map, Integer.valueOf(linee[2]))));
						((LandscapeNode) findNode(map, Integer.valueOf(linee[2]))).setEdge(((LandscapeNode)findNode(map, Integer.valueOf(linee[0]))));
					}					
				}												
			}			
		}		
		br.close();
		return map;
	}
	
	public ArrayList<String> extractNodeLines() throws IOException{
		ArrayList<String> nodes = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("iztech.izmap"));
		String line ="";
		while((line=br.readLine())!=null) {
			String[] linee = line.split(" ");
			if(!linee[0].equals("#")&&(line.contains("Building")||line.contains("Landscape"))) {
				nodes.add(line);
			}
		}
		br.close();
		return nodes;
	}
	
	public ArrayList<String> extractConnectionLines() throws IOException{
		ArrayList<String> connections = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("iztech.izmap"));
		String line ="";
		while((line=br.readLine())!=null) {
			String[] linee = line.split(" ");
			if(!linee[0].equals("#")&&line.contains("<-->")) {
				connections.add(line);
			}
		}
		br.close();
		return connections;
	}
	
	public Node findNode(HashMap< ? super Node,ArrayList<? super Node>> map ,int id) {
		Set<? super Node> keys = map.keySet();
		for(Object k: keys) {
			if(id==((Node) k).getId()) {
				return (Node) k;
			}			
		}		
		return null;
	}		
}
