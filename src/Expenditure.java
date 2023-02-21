
/**
 *
 */

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Harsha Ravuri
 *
 */
public class Expenditure {
	public String title;
	public double amount;
	public Date date;
	public String category;
	public String dateString;

	public Expenditure(String title, double amount, Date date, String category, String dateString) {
		setAmount(amount);
		setCategory(category);
		setDate(date);
		setTitle(title);
		this.dateString = dateString;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public void displayExpenditure() {
		String exp_details = "Title: " + title + "\n" +
				"Amount: " + amount + "\n" +
				"Date: " + dateString + "\n" +
				"Category: " + category;
		System.out.println(exp_details);
	}

}