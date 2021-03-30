package IztechMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import BusinessNode.BuildingNode;
import BusinessNode.LandscapeNode;
import BusinessNode.Node;
import BusinessNode.NodeIdComperator;
import BusinessNode.NodeNameComperator;
import GarphOperations.Graph;
import GarphOperations.PathFinder;

public class IztechMap {

	HashMap< ? super Node,ArrayList<? super Node>> ýztechMap;
	double[][] ýztechMapEdgesMatrix;
	ArrayList<Node> keys = new ArrayList<>();
	NodeIdComperator comp = new NodeIdComperator();
	@SuppressWarnings("unchecked")
	public IztechMap(HashMap< ? super Node,ArrayList<? super Node>> ýztechMap, double[][] ýztechMapEdgesMatrix) {
		this.ýztechMap= ýztechMap;
		this.ýztechMapEdgesMatrix= ýztechMapEdgesMatrix;
		keys.addAll((Collection<? extends Node>) ýztechMap.keySet());
		Collections.sort(keys, comp);
	}
	
	public void findpathNodes(Node node1, Node node2) {
		double dist = new PathFinder(ýztechMapEdgesMatrix).shortestDistBetweenTwoSources(keys.indexOf(node1),keys.indexOf(node2));
		printPath(node1, node2, dist);
	}
	
	public void possibleReachableLocationsFromGivenLocationAndDistance(Node node, double dist) {
		Set<? super Node> keys = ýztechMap.keySet();
		for(Object nodee: keys) {
			if(new PathFinder(ýztechMapEdgesMatrix).shortestDistBetweenTwoSources(node.getId()-1, ((Node)nodee).getId()-1)<dist) {
				printPath(node, (Node)nodee, new PathFinder(ýztechMapEdgesMatrix).shortestDistBetweenTwoSources(node.getId()-1, ((Node)nodee).getId()-1));
			}
		}
	}
	
	
	public void addBuildingNode(int id, Node.Category category, BuildingNode.Type type, String name, ArrayList<? super Node> listofConnections) {
		BuildingNode newNode = new BuildingNode(id,category,type,name);
		ýztechMap.put(newNode, listofConnections);
		for(int i =0;i<listofConnections.size();i++) {
			if(listofConnections.get(i).getClass().equals(BuildingNode.class)) {
				
				newNode.setEdge((BuildingNode)listofConnections.get(i));
				((BuildingNode)listofConnections.get(i)).setEdge(newNode);
			}
			else if(listofConnections.get(i).getClass().equals(LandscapeNode.class)) {
				
				newNode.setEdge((LandscapeNode)listofConnections.get(i));
				((LandscapeNode)listofConnections.get(i)).setEdge(newNode);
			}
			else {
				
				newNode.setEdge((Node)listofConnections.get(i));
				((Node)listofConnections.get(i)).setEdge(newNode);
			}
		}
		keys.add(newNode);
		ýztechMapEdgesMatrix = new Graph(ýztechMap).mapToGraph();
	}
	
	public void addLandscapeNode(int id, Node.Category category, LandscapeNode.Type type, String name, ArrayList<? super Node> listofConnections) {
		LandscapeNode newNode = new LandscapeNode(id, category, type, name);
		ýztechMap.put(newNode, new ArrayList<>());
		for(int i =0;i<listofConnections.size();i++) {
			if(listofConnections.get(i).getClass().equals(BuildingNode.class)) {
				ýztechMap.get(newNode).add((BuildingNode)listofConnections.get(i));
				ýztechMap.get(listofConnections.get(i)).add(newNode);
				newNode.setEdge((BuildingNode)listofConnections.get(i));
				((BuildingNode)listofConnections.get(i)).setEdge(newNode);				
			}
			else if(listofConnections.get(i).getClass().equals(LandscapeNode.class)) {
				ýztechMap.get(newNode).add((LandscapeNode)listofConnections.get(i));
				ýztechMap.get(listofConnections.get(i)).add(newNode);
				newNode.setEdge((LandscapeNode)listofConnections.get(i));
				((LandscapeNode)listofConnections.get(i)).setEdge(newNode);
			}
			else {
				ýztechMap.get(newNode).add((Node)listofConnections.get(i));
				ýztechMap.get(listofConnections.get(i)).add(newNode);
				newNode.setEdge((Node)listofConnections.get(i));
				((Node)listofConnections.get(i)).setEdge(newNode);
			}
		}
		keys.add(newNode);
		ýztechMapEdgesMatrix = new Graph(ýztechMap).mapToGraph();

	}
	
	public void removeNode(Node removedNode) {
		ýztechMap.remove(removedNode);
		keys.remove(removedNode);
		for(Node node: keys) {
			for(int i =0;i<node.getEdges().size();i++) {
				if(node.getEdges().get(i).getCorner2().equals(removedNode)||node.getEdges().get(i).getCorner1().equals(removedNode)) {
					node.getEdges().remove(i);
				}
				if(ýztechMap.get(node).contains(removedNode)) {
					ýztechMap.get(node).remove(removedNode);
				}
			}
		}
		ýztechMapEdgesMatrix = new Graph(ýztechMap).mapToGraph();
	}
	
	
	@SuppressWarnings("unchecked")
	public void printNeighboors(Node node) {
		ArrayList<Node> neighboorNodes = (ArrayList<Node>) ýztechMap.get(node);
		NodeNameComperator comp = new NodeNameComperator();
		Collections.sort(neighboorNodes, comp);
		for(Node nodee :neighboorNodes){
			System.out.println(nodee.getName());
		}
	}
	
	public void printPath(Node node1, Node node2, double dist) {
		System.out.println(node1.getName() + " to " + node2.getName() + " shortest paths dist is " + dist);
	}
	
	
}
