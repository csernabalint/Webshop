import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

public class Webshop {
	
	private Logger logger;
	private Map<String, Customer> customers;
    private Map<String, Payment> payments;
	
	public Webshop() {
        customers = new HashMap<>();
        payments = new HashMap<>();
		try {
			logger = Logger.getLogger(Webshop.class.getName());
			FileHandler fh = new FileHandler("application.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void readCustomersCSV(String fileName) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
		String line;
		while ((line = reader.readLine()) != null) {
			String[] values = line.split(";");
	        if (values.length == 4) {
	        	String webshopID = values[0];
	        	String customerID = values[1];
	        	String customerName = values[2];
	        	String customerAddress = values[3];
	        	customers.put(customerID,new Customer(webshopID,customerID,customerName,customerAddress));
 	        } else {
 	        	logger.log(Level.WARNING, "A sor átugrása az attribútumok helytelen száma miatt: " + line);
			}
		}
		reader.close();
		} catch (IOException e) {
            logger.log(Level.SEVERE, "Hiba történt az ügyfelek beolvasása során.", e);
        }
	}
	
	
	public void readPaymentsCSV(String fileName) throws IOException, ParseException{

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
		String line;
		while ((line = reader.readLine()) != null) {
			String[] values = line.split(";");
	        if (values.length == 7) {
	        	String webshopID = values[0];
	        	String customerID = values[1];
	        	String paymentType = values[2];
	        	Double amount = Double.parseDouble(values[3]);
	        	String bankAccountNumber = values[4];
	        	String cardNumber = values[5];
	        	LocalDate paymentDate = LocalDate.parse(values[6], DateTimeFormatter.ofPattern("yyyy.MM.dd"));             
	        	payments.put(customerID,new Payment(webshopID,customerID,paymentType,amount,
	        			bankAccountNumber,cardNumber,paymentDate));
 	        	} else {
 	 	        	logger.log(Level.WARNING, "A sor átugrása az attribútumok helytelen száma miatt: " + line);
 				}
	        }
		reader.close();
		} catch (IOException | DateTimeParseException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Hiba történt a kifizetések CSV. fájl beolvasása során", e);
        }
	}

	public Map<String, Customer> getCustomersList(){
		return customers;
	}
	
	public Map<String, Payment> getPaymentsList() {
		return payments;
	}
}
