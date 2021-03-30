package BusinessNode;

public class LandscapeNode extends Node{
	public enum Type{Waterfall, Beach, HistoricalRuin}
	private Type type;
	
	public LandscapeNode(int id, Category category, Type type, String name) {
		super(id ,category, name);
		this.type = type;
	}
	
	public void setEdge(LandscapeNode toLandscapeNode) {
		super.setEdge((Node)toLandscapeNode);
		
	}
	public void setEdge(BuildingNode toBuildingNode) {
		
		if(type.equals(Type.HistoricalRuin)&&toBuildingNode.getType().equals(BuildingNode.Type.Cafeteria)) {
			getEdges().add(new Edge(this, toBuildingNode, Math.pow(getdefaultDistance(), 2)));
		}
		else {
			super.setEdge((Node)toBuildingNode);	
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
		if(getType().equals(Type.HistoricalRuin)) {
			return getId() + " [" + getCategory() + ", " + "Historical Ruin" + ", " + getName() + "]";	

		}
		return getId() + " [" + getCategory() + ", " + getType() + ", " + getName() + "]";	
	}
	
}
