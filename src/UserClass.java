import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * User Class for expenditure activities
 */

/**
 * @author Harsha Ravuri
 *
 */
public class UserClass {
	//Making a new Expenditure list
	static ExpenditureList exp_list = new ExpenditureList();
	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		//making new expenditure
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		Expenditure exp = new Expenditure("Potatoes", 5.00, now, "Food");
		
		//making 2nd expenditure 
		LocalDateTime now2 = LocalDateTime.now(); 
		Expenditure exp2 = new Expenditure("Oranges", 18.95, now, "Food");
		
		//making 3rd expenditure
		LocalDateTime now3 = LocalDateTime.now();  
		Expenditure exp3 = new Expenditure("Car Insurance", 400.00, now, "Transportation");
		
		exp_list.addExpenditure(exp);
		exp_list.addExpenditure(exp2);
		exp_list.addExpenditure(exp3);
		
		//Display all expenditures
		exp_list.displayExpenditureList();
		
		//editing exp2
		exp_list.editExpenditure(exp2, "Bath supplies", 5.00, "Household");
		exp_list.deleteExpenditure(exp3);
		exp_list.deleteExpenditure(exp);
		System.out.println("-------------------------------------------------------------");
		exp_list.displayExpenditureList();
	}

}
