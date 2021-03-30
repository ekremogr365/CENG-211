package BusinessNode;

public class Edge {
	
	private Node corner1;
	private Node corner2;
	private double distance;
	
	public Edge(Node corner1, Node corner2,double distance)  {
		this.corner1 = corner1;
		this.corner2 = corner2;
		this.distance= distance;
 	}
	
	

	@Override
	public boolean equals(Object otherObject) {
		if(otherObject==null) {return false;}
		else if(getClass()!=otherObject.getClass()) {return false;}
		else {
			Edge otherEdge = (Edge) otherObject;			
			return (corner1.equals(otherEdge.getCorner1()) && corner2.equals(otherEdge.getCorner2()) && distance==otherEdge.getDistance());
		}
	}



	public Node getCorner1() {
		return corner1;
	}

	public void setCorner1(Node corner1) {
		this.corner1 = corner1;
	}

	public Node getCorner2() {
		return corner2;
	}

	public void setCorner2(Node corner2) {
		this.corner2 = corner2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}
