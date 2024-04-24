import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Report {
	
	private HashMap<String, Double> customerTotalSpending = new HashMap<>();
	
	
	public void customerReport(Map<String, Customer> customers, Map<String, Payment> payments,String outputFileName) {
        
        
        for (Payment payment : payments.values()) {
            String customerID = payment.getCustomerID();
            double amount = payment.getAmount();
            
            if (customerTotalSpending.containsKey(customerID)) {
            
                double totalSpending = customerTotalSpending.get(customerID);
                totalSpending += amount;
                customerTotalSpending.put(customerID, totalSpending);
            } else {
               
            	customerTotalSpending.put(customerID, amount);
            }
        }
        
 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write("NAME, ADDRESS, TOTAL AMOUNT\n");
            for (Customer customer : customers.values()) {
            	
                String customerID = customer.getCustomerID();
                String customerName = customer.getCustomerName();
                String customerAddress = customer.getCustomerAddress();
                double totalSpending = customerTotalSpending.getOrDefault(customerID, 0.0);
                writer.write(customerName + ", " + customerAddress + ", " + totalSpending + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public  void topCustomersReport(Map<String, Customer> customers) {

		 List<String> topCustomersID = 
			        customerTotalSpending.entrySet().stream()
			            .sorted(Map.Entry.<String, 
			            		Double>comparingByValue().reversed())
			            .limit(2)
			            .map(Map.Entry::getKey)
			            .collect(Collectors.toList());
		 
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("top.csv"))) {
	            writer.write("NAME, ADDRESS, TOTAL AMOUNT\n");
	            for (String id : topCustomersID) {
	            	
	                Customer c = customers.get(id);
	                String customerName = c.getCustomerName();
	                String customerAddress = c.getCustomerAddress();
	                double totalSpending = customerTotalSpending.getOrDefault(id, 0.0);
	                writer.write(customerName + ", " + customerAddress + ", " + totalSpending + "\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 }
	
	
	
	public void webshopReport(Map<String, Customer> customers, Map<String, Payment> payments) {
	    HashMap<String, Double> webshopTotalCardIncome = new HashMap<>();
	    HashMap<String, Double> webshopTotalTransferIncome = new HashMap<>();

	    for (Payment payment : payments.values()) {
	        String webshopID = payment.getWebshopID();
	        double amount = payment.getAmount();
	        String paymentType = payment.getPaymentType();
	        
	        if (paymentType.equals("card")) {
	            webshopTotalCardIncome.put(webshopID, webshopTotalCardIncome.getOrDefault(webshopID, 0.0) + amount);
	        } else if (paymentType.equals("transfer")) {
	            webshopTotalTransferIncome.put(webshopID, webshopTotalTransferIncome.getOrDefault(webshopID, 0.0) + amount);
	        }
	    }

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("report02.csv"))) {
	        writer.write("WEBSHOP, CARD_PAYMENTS, TRANSFER_PAYMENTS\n");
	        for (String webshopID : webshopTotalTransferIncome.keySet()) 
	        {
	        	
	            double cardPayments = webshopTotalCardIncome.getOrDefault(webshopID, 0.0);
	            double transferPayments = webshopTotalTransferIncome.getOrDefault(webshopID, 0.0);
	            writer.write(webshopID + ", " + cardPayments + ", " + transferPayments + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
