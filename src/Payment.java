import java.sql.Date;
import java.time.LocalDate;

public class Payment {
	private String webshopID;
	private String customerID;
	private String paymentType;
	private Double amount;
	private String bankAccountNumber;
	private String cardNumber;
	private LocalDate  paymentDate;
	
	public Payment(String webshopID, String customerID,
				   String paymentType, Double amount,
				   String bankAccountNumber, String cardNumber,
				   LocalDate  paymentDate) {
		
		this.webshopID = webshopID;
		this.customerID = customerID;
		this.paymentType = paymentType;
		this.amount = amount;
		this.bankAccountNumber = bankAccountNumber;
		this.cardNumber = cardNumber;
		this.paymentDate = paymentDate;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public String getCustomerID() {
		return customerID;
	}

	public String getWebshopID() {
		return webshopID;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
}
