package BusinessNode;


public class BuildingNode extends Node{
	

	public enum Type{Department, Cafeteria, Administrative, Facility }
	private Type type;
	
	public BuildingNode(int id, Category category, Type type, String name) {
		super(id, category, name);
		this.type = type;
	}

	
	
	
	
	public void setEdge(BuildingNode toBuildingNode) {
	
		if(type.equals(Type.Department)&&toBuildingNode.getType().equals(Type.Cafeteria)||type.equals(Type.Cafeteria)&&toBuildingNode.getType().equals(Type.Department)) {
			getEdges().add(new Edge(this, toBuildingNode, (getdefaultDistance()*2)-3));
		}
		else if(type.equals(Type.Administrative)&&toBuildingNode.getType().equals(Type.Department)||type.equals(Type.Department)&&toBuildingNode.getType().equals(Type.Administrative)) {
			getEdges().add(new Edge(this, toBuildingNode, getdefaultDistance()/2));
		}
		else if(type.equals(Type.Cafeteria)&&toBuildingNode.getType().equals(Type.Facility)||type.equals(Type.Facility)&&toBuildingNode.getType().equals(Type.Cafeteria)) {
			getEdges().add(new Edge(this, toBuildingNode, Math.abs(Math.sqrt(getdefaultDistance()))));
		}
		else {
			super.setEdge((Node)toBuildingNode);
		}
	}
	
	public void setEdge(LandscapeNode toLandscapeNode) {
		if(type.equals(Type.Cafeteria)&&toLandscapeNode.getType().equals(LandscapeNode.Type.Waterfall)) {
			getEdges().add(new Edge(this, toLandscapeNode, getdefaultDistance()/3));
		}
		else if(type.equals(Type.Facility)&&toLandscapeNode.getType().equals(LandscapeNode.Type.Waterfall)) {
			getEdges().add(new Edge(this, toLandscapeNode, getdefaultDistance()*5/2));
		}
		else if(type.equals(Type.Department)&&toLandscapeNode.getType().equals(LandscapeNode.Type.Beach)) {
			getEdges().add(new Edge(this, toLandscapeNode, ((getdefaultDistance()*getdefaultDistance())/2)+4));
		}
		else {
			super.setEdge((Node)toLandscapeNode);
		}		
	}




	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}





	@Override
	public String toString() {
		return getId() + " [" + getCategory() + ", " + getType() + ", " + getName() + "]";
	}
	
	
	
}
