package GarphOperations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import BusinessNode.Node;
import BusinessNode.NodeIdComperator;

public class Graph {

	HashMap< ? super Node,ArrayList<? super Node>> map;
	ArrayList<Node> keys = new ArrayList<>();
	NodeIdComperator comp = new NodeIdComperator();
	double[][] graph;
	
	@SuppressWarnings("unchecked")
	public Graph(HashMap< ? super Node,ArrayList<? super Node>> map) {
		this.map = map;
		graph= mapToGraph();
		keys.addAll((Collection<? extends Node>) map.keySet());
		Collections.sort(keys, comp);
	}
	
	
	public double[][] mapToGraph(){
		double[][] IztechmMap = new double[map.size()][map.size()];
		for(Object node: keys) {
			for(int j =0;j<((Node)node).getEdges().size();j++) {
				IztechmMap[keys.indexOf(node)][keys.indexOf(((Node)node).getEdges().get(j).getCorner2())] = ((Node)node).getEdges().get(j).getDistance();
			}
		}
		System.out.println(map.size());

		return IztechmMap;		
	}

	public double[][] getGraph() {
		return graph;
	}
	
	
	
}
