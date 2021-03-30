import java.util.Date;

public class Bill {

	private int id;
	private int flat_Id;
	private int amount;
	private String type;
	private boolean payment_İnfo;
	private Date payment_Deadline_date;
	private Date last_Update_date;
	
	@SuppressWarnings("null")
	public Bill() {
		
		id = 0;
		flat_Id = 0;
		amount = 0;
		type = null;
		payment_İnfo = (Boolean) null;
		payment_Deadline_date = null;
		payment_Deadline_date= null;
		last_Update_date = null;
	}
	
	
	public Bill(int id, int flat_Id, int amount, String type, boolean payment_İnfo, Date payment_Deadline_date,
			Date last_Update_date) {
		this.id = id;
		this.flat_Id = flat_Id;
		this.amount = amount;
		this.type = type;
		this.payment_İnfo = payment_İnfo;
		this.payment_Deadline_date = payment_Deadline_date;
		this.last_Update_date = last_Update_date;
	}
	
	
	
	
	public String toString() {
		
		return "[ id= " + id + " Flat id= " + flat_Id + " Amount= " + amount + " Type= " + type.substring(0, 4) + ". Payment info: " + payment_İnfo +
				" Payment deadline date: " + (payment_Deadline_date.getYear()+1900)+"-"+(payment_Deadline_date.getMonth()+1)+"-"+payment_Deadline_date.getDate() 
				+ " Last update date: " + (last_Update_date.getYear()+1900) +"-" + (last_Update_date.getMonth()+1)+"-"+ last_Update_date.getDate() + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getFlat_Id() {
		return flat_Id;
	}


	public void setFlat_Id(int flat_Id) {
		this.flat_Id = flat_Id;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public boolean isPayment_İnfo() {
		return payment_İnfo;
	}


	public void setPayment_İnfo(boolean payment_İnfo) {
		this.payment_İnfo = payment_İnfo;
	}


	public Date getPayment_Deadline_date() {
		return payment_Deadline_date;
	}


	public void setPayment_Deadline_date(Date payment_Deadline_date) {
		this.payment_Deadline_date = payment_Deadline_date;
	}


	public Date getLast_Update_date() {
		return last_Update_date;
	}


	public void setLast_Update_date(Date last_Update_date) {
		this.last_Update_date = last_Update_date;
	}
	
	
}
