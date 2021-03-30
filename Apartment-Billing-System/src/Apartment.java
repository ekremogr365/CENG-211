
public class Apartment {
	
	private Flat[][] apartment;
	private int max_floor_Num;
	private int max_flat_NUm;
	
	public Apartment(int max_floor_Num, int max_flat_NUm) {
		
		this.max_floor_Num = max_floor_Num;
		this.max_flat_NUm = max_flat_NUm;
		Flat[][] apartment = new Flat[max_floor_Num][max_flat_NUm];
		this.apartment = apartment;
	}
	
	public void add(Flat a, int x,int y) {
		apartment[x][y] = a;
		
	}

	
	public Flat[][] getApartment() {
		return apartment;
	}

	public void setApartment(Flat[][] apartment) {
		this.apartment = apartment;
	}

	public int getMax_floor_Num() {
		return max_floor_Num;
	}

	public void setMax_floor_Num(int max_floor_Num) {
		this.max_floor_Num = max_floor_Num;
	}

	public int getMax_flat_NUm() {
		return max_flat_NUm;
	}

	public void setMax_flat_NUm(int max_flat_NUm) {
		this.max_flat_NUm = max_flat_NUm;
	}
	
	
}
