import java.io.IOException;
import java.text.ParseException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

public class MainClass {

	public static void main(String[] args) {
		
		Webshop webshop1 = new Webshop();
		
		try {
			webshop1.readCustomersCSV("customer.csv");
			webshop1.readPaymentsCSV("payments.csv");
			Report report = new Report();
			report.customerReport(webshop1.getCustomersList(), 
								  webshop1.getPaymentsList(), "report01.csv");
			report.topCustomersReport(webshop1.getCustomersList());
			report.webshopReport(webshop1.getCustomersList(), 
								  webshop1.getPaymentsList());
		} catch (IOException | ParseException  e) {
			e.printStackTrace(); 
		}
	}
}
