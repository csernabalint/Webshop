
public class Customer {
	
	
	private String webshopID;
	private String customerID;
	private String customerName;
	private String customerAddress;
	
	public Customer(String webshopID, String customerID, 
			String customerName, String customerAddress) {
		
		this.webshopID = webshopID;
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}
}
