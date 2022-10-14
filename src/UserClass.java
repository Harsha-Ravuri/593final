import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * User Class for expenditure activities
 */

/**
 * @author Harsha Ravuri
 *
 */
public class UserClass {
	// Making a new Expenditure list
	static ExpenditureList exp_list = new ExpenditureList();

	/**
	 * @param args
	 */

  static void menu() {
		int selection;
		Scanner input = new Scanner(System.in);

		/***************************************************/

		System.out.println("Choose from these choices");
		System.out.println("-------------------------\n");
		System.out.println("1 - Add Expense");
		System.out.println("2 - Edit Expense");
		System.out.println("3 - Edit Exppense");
		System.out.println("4 - Show All Expenses");
		System.out.println("5 - Quit");

		selection = input.nextInt();
		System.out.println(selection);  
  }

	 public static void main(String[] args) {
		// making new expenditure
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Expenditure exp = new Expenditure("Potatoes", 5.00, now, "Food");

		// making 2nd expenditure
		LocalDateTime now2 = LocalDateTime.now();
		Expenditure exp2 = new Expenditure("Oranges", 18.95, now, "Food");

		// making 3rd expenditure
		LocalDateTime now3 = LocalDateTime.now();
		Expenditure exp3 = new Expenditure("Car Insurance", 400.00, now, "Transportation");

		exp_list.addExpenditure(exp);
		exp_list.addExpenditure(exp2);
		exp_list.addExpenditure(exp3);

		// Display all expenditures
		exp_list.displayExpenditureList();

		// editing exp2
		exp_list.editExpenditure(exp2, "Bath supplies", 5.00, "Household");
		System.out.println("-------------------------------------------------------------");
		exp_list.displayExpenditureList();

		menu();

	}

}
