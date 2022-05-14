package model;

public class ReqComplain {
	private String id;
	private String customerID;
	private String type;
	private String description;
	
	
	public ReqComplain(String id, String customerID, String type, String description) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.type = type;
		this.description = description;
	}


	public String getId() {
		return id;
	}


	public String getCustomerID() {
		return customerID;
	}


	public String getType() {
		return type;
	}


	public String getDescription() {
		return description;
	}
}
