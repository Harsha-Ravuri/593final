import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

class ExpenditureListTest {
	ExpenditureList exp_list = new ExpenditureList();
	//making new expenditure
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    Date now = new Date();


	Expenditure exp = new Expenditure("Potatoes", 5.00, now, "Food","02-12-2023");
	Expenditure exp_to_edit = new Expenditure("Vacuum", 25.00, now, "Clothing","02-14-2023");

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

	@Test
	void test_count() {
		exp_list.addExpenditure(exp);
		int actual_count = exp_list.getCount();
		int expected_count = 1;
		Assert.assertTrue(actual_count==1);
	}



}
