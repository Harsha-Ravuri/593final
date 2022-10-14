import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

class ExpenditureTest {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime date_exp_added = LocalDateTime.now(); 
	Expenditure test_expenditure = new Expenditure("Cereal", 8.65, date_exp_added, "Food"); 

	@Test
	void testGetTitle() {
		String expectedTitle = "Cereal";
		String actualTitle = test_expenditure.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
	}

	@Test
	void testGetAmount() {
		double expectedAmount = 8.65;
		double actualAmount = test_expenditure.getAmount();
		Assert.assertEquals(expectedAmount, actualAmount, 0);
	}
	
	@Test
	void testGetDate() {
		LocalDateTime expectedDate = date_exp_added;
		LocalDateTime actualDate = test_expenditure.getDate();
		/**
		 * Date.compareTo() returns: 
		 * the value 0 if the argument Date is equal to this Date; 
		 * a value less than 0 if this Date is before the Date argument; 
		 * and a value greater than 0 if this Date is after the Date argument
		 * */
		Assert.assertTrue(expectedDate.compareTo(actualDate)==0);
	}

	@Test
	void testGetCategory() {
		String expectedCategory = "Food";
		String actualCategory = test_expenditure.getCategory();
		Assert.assertEquals(expectedCategory, actualCategory);
	}

}
