package BusinessNode;

import java.util.ArrayList;

public abstract class Node{
	
	public enum Category {Building,Landscape}
	
	private int id;
	private Category category;
	private String name;
	private final int defaultDistance = 6;
	private ArrayList<Edge> edges = new ArrayList<>();
	
	public Node(int id ,Category category, String name) {
		this.id=id;
		this.category=category;
		this.name = name;
		
	}
	
	
	public void deleteEgualEdges() {
		int counter=0;
		for(int i =0;i<edges.size();i++) {
			for(int j = 0;j<edges.size();j++) {
				if(edges.get(i).equals(edges.get(j))) {
					counter++;
				}
			}
			if(counter>1) {
				edges.remove(i);
			}
			counter=0;
		}
	}
	
	public void setEdge(Node toNode) {
		edges.add(new Edge(this, toNode, defaultDistance));
		deleteEgualEdges();
	}
	
	abstract void setEdge(LandscapeNode toLandscapeNode);
	
	abstract void setEdge(BuildingNode toBuildingNode);


	
	
	@Override
	public boolean equals(Object otherObject) {
		if(otherObject==null) {return false;}
		else if(getClass()!=otherObject.getClass()) {return false;}
		else {
			Node otherNode = (Node) otherObject;			
			return (category.equals(otherNode.getCategory()) && name.equals(otherNode.getName()));
		}
	}


	public int getdefaultDistance() {
		return defaultDistance;
	}
	
	
	
	public int getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}


	@Override
	public String toString() {
		return getId() + " [" + getCategory() + ", " + ", " + getName() + "]";
	}
	
	
	
	
}
