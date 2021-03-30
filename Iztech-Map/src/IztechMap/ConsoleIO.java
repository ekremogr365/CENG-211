package IztechMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import BusinessNode.BuildingNode;
import BusinessNode.LandscapeNode;
import BusinessNode.Node;
import FileOperations.FileOutput;

public class ConsoleIO {

	IztechMap ýztechMap;
	ArrayList<? super Node> keyList;
	public ConsoleIO(IztechMap ýztechMap) {
		this.ýztechMap=ýztechMap;
		keyList= addKeySetToList();
	}
	@SuppressWarnings("resource")
	public void Start() throws IOException {
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("*****************");
			System.out.println("1.Find the shortest path between given two nodes \n2.Add node\n3.Remove node"
						+ "\n4.Possible reachable locations from a given location and distance\n5.Print the neighbor nodes "
						+	"\n6.Exit");
			if(scan.hasNextInt()) {
				int operation = scan.nextInt();
				switch (operation) {
				case 1:
					printNodes();
					int id1 = scanNodeId();
					int id2 = scanNodeId();
					if(checkID(id1)&&checkID(id2)) {
						Node node1 = findNode(id1);
						Node node2 = findNode(id2);
						if(node1!=null&&node2!=null)
							ýztechMap.findpathNodes(node1,node2);
					}else
						System.out.println("At least one id that you are entered not mach with any node.");				
					break;
				case 2:
					int id = scanNodeId();
					if(!checkID(id)) {
						Node.Category categ = scanCategory();
						if(categ.equals(Node.Category.Building)) {
							ýztechMap.addBuildingNode(id,categ, scanBuildingNode(), scanName(), listOfSpecificNodes());
						}
						else if(categ.equals(Node.Category.Landscape)) {
							ýztechMap.addLandscapeNode(id,categ, scanLandscapeNode(), scanName(), listOfSpecificNodes());
						}
					}
					else
						System.out.println("Adding node not succes. There exist a node has id number " + id );
					break;
					
				case 3:
					printNodes();
					int idd = scanNodeId();
					if(checkID(idd)) {
						Node removednode = findNode(idd);
						ýztechMap.removeNode(removednode);
					}
					else
						System.out.println("Id that you are entered not mach with any node.");				
					break;
				case 4:
					printNodes();
					int iddd = scanNodeId();
					if(checkID(iddd)) {
						Node nodee = findNode(iddd);
						ýztechMap.possibleReachableLocationsFromGivenLocationAndDistance(nodee, scanDýst());
					}	
					else
						System.out.println("Id that you are entered not mach with any node.");				
					break;
				case 5:
					printNodes();
					int idddd = scanNodeId();
					if(checkID(idddd)) {
						Node nodeee = findNode(idddd);
						ýztechMap.printNeighboors(nodeee);
					}
					else
						System.out.println("Id that you are entered not mach with any node.");									
					break;
				case 6:
					new FileOutput("iztech.izmap").overWriteFile(ýztechMap.ýztechMap);
					System.exit(0);
					break;
			    }		
			}			
		}					
	}
	
	public void printNodes() {
		for(int i=0;i<ýztechMap.keys.size();i++) {
			Node nodei = ýztechMap.keys.get(i);
			if(nodei.getCategory().equals(Node.Category.Building)) {
				BuildingNode nodeii = (BuildingNode) nodei;  
				System.out.println(nodei.getId()+"["+nodei.getCategory()+","+ nodeii.getType() + " ," + nodei.getName()+"]");
			}
			else if(nodei.getCategory().equals(Node.Category.Landscape)) {
				LandscapeNode nodeii = (LandscapeNode) nodei;  
				System.out.println(nodei.getId()+"["+nodei.getCategory()+","+ nodeii.getType() + " ," + nodei.getName()+"]");
				
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<? super Node > addKeySetToList(){
		ArrayList<? super Node> keyList = new ArrayList<>();
		Set<? super Node> keySet = ýztechMap.ýztechMap.keySet();
		Iterator<Object> ýterator = (Iterator<Object>) keySet.iterator();
		while(ýterator.hasNext()) {
			keyList.add((Node)ýterator.next());
		}
		return keyList;
	}
	@SuppressWarnings("resource")
	private ArrayList<? super Node > listOfSpecificNodes(){
		ArrayList<? super Node> nodes = new ArrayList<>();
		System.out.println("Which building are the neigboors to new building?(Enter id number)");
		addSpecifcNodeToArrayList(nodes);
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("1.Continue to add \n2.Stop");
			if(scan.hasNextInt()) {
				int operation = scan.nextInt();
				switch (operation) {
				case 1:
					addSpecifcNodeToArrayList(nodes);
					break;
				case 2:
					return nodes;
				}
			}
		}
	}			
	@SuppressWarnings("resource")
	private	ArrayList<? super Node > addSpecifcNodeToArrayList(ArrayList<? super Node > list){
		Scanner scan = new Scanner(System.in);
		int ýd = 0;
		printNodes();
		if(scan.hasNextInt()) {
			ýd=scan.nextInt();
			if(findNode(ýd)!=null)
				list.add(findNode(ýd));
		
		}
		return list;
	}	
	
	private Node findNode(int id) {
		for(int i =0;i< ýztechMap.keys.size();i++) {
			if(ýztechMap.keys.get(i).getId()==id)
				return ýztechMap.keys.get(i);
		}
		return null;
	}
	private boolean checkID(int id) {
		for(Node node:ýztechMap.keys) {
			if(node.getId()==id)
				return true;
		}
		return false;
	}
	
	@SuppressWarnings("resource")
	private int scanNodeId(){
		Scanner scan = new Scanner(System.in);
		int ýd = 0;
		System.out.println("Node ýd: ");
		if(scan.hasNextInt()) {
			ýd = scan.nextInt();
			return ýd;
		}else
			System.out.println("Wrong Input!");
		return scanNodeId();
	}
	
	@SuppressWarnings("resource")
	private String scanName() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Name: ");
		String name = scan.nextLine();
		return name;
	}
	
	@SuppressWarnings("resource")
	private int scanDýst(){
		Scanner scan = new Scanner(System.in);
		int ýd = 0;
		System.out.println("Dýstance: ");
		if(scan.hasNextInt()) {
			ýd = scan.nextInt();
			return ýd;
		}else
			System.out.println("Wrong Input!");
		return scanDýst();
	}
	
	@SuppressWarnings("resource")
	private LandscapeNode.Type scanLandscapeNode() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1.Beach\n2.HistoricalRuin\n3.Waterfall");
		if(scan.hasNextInt()) {
			int operation = scan.nextInt();
			switch (operation) {
			case 1:
				return LandscapeNode.Type.Beach;
			case 2:
				return LandscapeNode.Type.HistoricalRuin;
			case 3:
				return LandscapeNode.Type.Waterfall;

			default:
				System.out.println("Wrong input");
				scanLandscapeNode();
			}
		}
		else 
			System.out.println("Wrong input");
			return scanLandscapeNode();
		
	}
	@SuppressWarnings("resource")
	private BuildingNode.Type scanBuildingNode() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1.Department\n2.Cafeteria\n3.Administrative\n4.Facility");
		if(scan.hasNextInt()) {
			int operation = scan.nextInt();
			switch (operation) {
			case 1:
				return BuildingNode.Type.Department;
			case 2:
				return BuildingNode.Type.Cafeteria;
			case 3:
				return BuildingNode.Type.Administrative;
			case 4:
				return BuildingNode.Type.Facility;
			default:
				System.out.println("Wrong input");
				scanBuildingNode();
			}
		}
		else 
			System.out.println("Wrong input");
			return scanBuildingNode();
	}
	
	@SuppressWarnings("resource")
	private Node.Category scanCategory() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1.Building\n2.Landscape");
		if(scan.hasNextInt()) {
			int operation = scan.nextInt();
			switch (operation) {
			case 1:
				return Node.Category.Building;		
			case 2:
				return Node.Category.Landscape;
			default:
				System.out.println("Wrong input");
				scanCategory();
			}
		}
		else 
			System.out.println("Wrong input");
			return scanCategory();
		
			
	}
}
