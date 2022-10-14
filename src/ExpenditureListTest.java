import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

class ExpenditureListTest {
	ExpenditureList exp_list = new ExpenditureList();
	//making new expenditure
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now(); 
	
	/**
	//making 2nd expenditure 
	LocalDateTime now2 = LocalDateTime.now(); 
	Expenditure exp2 = new Expenditure("Oranges", 18.95, now, "Food");
	
	//making 3rd expenditure
	LocalDateTime now3 = LocalDateTime.now();  
	Expenditure exp3 = new Expenditure("Car Insurance", 400.00, now, "Transportation");
	 * */
	Expenditure exp = new Expenditure("Potatoes", 5.00, now, "Food");
	Expenditure exp_to_edit = new Expenditure("Vacuum", 25.00, now, "Clothing");
	
	@Test
	void testAddExpenditure() {
		exp_list.addExpenditure(exp);
		Assert.assertTrue(exp_list.getExpenditureList().contains(exp) == true);
	}

	@Test
	void testDeleteExpenditure() {
		exp_list.deleteExpenditure(exp);
		Assert.assertTrue(exp_list.getExpenditureList().contains(exp) == false);
	}

	@Test
	void testEditExpenditure() {
		exp_list.addExpenditure(exp_to_edit);
		exp_list.editExpenditure(0, exp_to_edit.getTitle(), exp_to_edit.getAmount(), "Household");
		Assert.assertTrue(exp_to_edit.getCategory()=="Household");
	}

}
