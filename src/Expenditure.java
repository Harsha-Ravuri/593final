/**
 * 
 */

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
/**
 * @author Harsha Ravuri
 *
 */
public class Expenditure {
	public String title;
	public double amount;
	public LocalDateTime date;
	public String category;
	
	public Expenditure(String title,double amount,LocalDateTime date,String category) {
		setAmount(amount);
		setCategory(category);
		setDate(date);
		setTitle(title);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	} 
	
	public void displayExpenditure() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String exp_details = 
				"Title: "+title+"\n" + 
			    "Amount: "+amount+"\n" +
				"Date: "+dtf.format(date) + "\n" + 
				"Category: "+category+"\n";
		System.out.println(exp_details);
	}
	
}
