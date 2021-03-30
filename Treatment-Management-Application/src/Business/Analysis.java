package Business;

public class Analysis {
	
	private boolean isready;
	private long startTime; 
	public Analysis() {
		startTime = System.currentTimeMillis(); 
		isready= false;
	}
	public boolean isIsready() {
		return isready;
	}
	public void setIsready(boolean isready) {
		this.isready = isready;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	
}
