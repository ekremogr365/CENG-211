import java.util.ArrayList;

public class Flat {

	
  	private int id;
  	private int floor_Number;
  	private int flat_Number;
  	private int numberof_Rooms;
  	private int square_Meter;
  	private ArrayList<Bill> listof_Bills;
    
    public Flat() {
        id = 0;
        floor_Number = 0;
        flat_Number = 0;
        numberof_Rooms = 0;
        square_Meter = 0;
        listof_Bills = null;       
    }

    public Flat(int id, int floor_Number,int flat_Number,int numberof_Rooms,int square_Meter,ArrayList<Bill> listof_Bills) {
        this.id = id;
        this.floor_Number = floor_Number;
        this.flat_Number = flat_Number;
        this.numberof_Rooms =numberof_Rooms;
        this.square_Meter = square_Meter;
        this.listof_Bills = listof_Bills;        
    }

    public String toString() {
    	
    	return "[id= " + id + " Floor Number= " + floor_Number + " Flat_Number= " + flat_Number + 
    			" Number of Rooms= " + numberof_Rooms + " Square Meter= " + square_Meter  +"]";   	
    }

	
    public int getId() {
		return id;
	}	
    public void setId(int id) {
		this.id = id;
	}

	public int getFloor_Number() {
		return floor_Number;
	}

	public void setFloor_Number(int floor_Number) {
		this.floor_Number = floor_Number;
	}

	public int getFlat_Number() {
		return flat_Number;
	}

	public void setFlat_Number(int flat_Number) {
		this.flat_Number = flat_Number;
	}

	public int getNumberof_Rooms() {
		return numberof_Rooms;
	}

	public void setNumberof_Rooms(int numberof_Rooms) {
		this.numberof_Rooms = numberof_Rooms;
	}

	public int getSquare_Meter() {
		return square_Meter;
	}

	public void setSquare_Meter(int square_Meter) {
		this.square_Meter = square_Meter;
	}

	public ArrayList<Bill> getListof_Bills() {
		return listof_Bills;
	}

	public void setListof_Bills(ArrayList<Bill> listof_Bills) {
		this.listof_Bills = listof_Bills;
	}
    
    
    
}

