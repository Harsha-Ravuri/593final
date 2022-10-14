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
	static String[] categoryArray = new String[] { "Food", "Clothing", "Gas", "Entertainment", "Other" };
	static boolean running = true;

	/**
	 * @param args
	 */

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	static void addExpenditure() {
		int selection;
		Scanner input = new Scanner(System.in);
		LocalDateTime currentDate = LocalDateTime.now();
		System.out.println("Please Enter The Title: ");
		String title = input.nextLine();
		Double amount = 0.0;

		boolean valid = false;
		while (valid == false) {
			System.out.println("Please Enter The Amount: ");
			Scanner newInput = new Scanner(System.in);
			try {
				amount = round(newInput.nextDouble(), 2);
				if (amount >= 0 && amount <= 1000000) {
					valid = true;
				} else {
					System.out.println("Invalid Input! ");
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}

		System.out.println("Please Select the Category: ");
		System.out.println("\n");
		System.out.println("Category Options: \n");
		for (int i = 0; i < categoryArray.length; i++) {
			System.out.println(i + " - " + categoryArray[i]);
		}
		System.out.println("\n");

		selection = input.nextInt();
		String category;
		category = categoryArray[selection];

		Expenditure exp = new Expenditure(title, amount, currentDate, category);
		exp_list.addExpenditure(exp);
		exp_list.displayExpenditureList();
	}

	static void updateExpenditure(int id, Expenditure exp) {
		int selection;
		Scanner input = new Scanner(System.in);
		String title = exp.getTitle();
		double amount = exp.getAmount();
		String category = exp.getCategory();

		System.out.println("Please Enter The Title (Press ENTER to Skip): ");
		if (!input.nextLine().isEmpty()) {
			title = input.nextLine();
		}

		boolean valid = false;
		while (valid == false) {
			System.out.println("Please Enter The Amount (Press ENTER to Skip): ");
			Scanner newInput = new Scanner(System.in);
			if (newInput.nextLine().isEmpty()) {
				break;
			}
			try {
				amount = round(newInput.nextDouble(), 2);
				if (amount >= 0 && amount <= 1000000) {
					valid = true;
				} else {
					System.out.println("Invalid Input! ");
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}

		System.out.println("Please Select the Category (Press ENTER to Skip): ");
		System.out.println("\n");
		System.out.println("Category Options: \n");
		for (int i = 0; i < categoryArray.length; i++) {
			System.out.println(i + " - " + categoryArray[i]);
		}
		System.out.println("\n");
		if (!input.nextLine().isEmpty()) {
			selection = input.nextInt();
			category = categoryArray[selection];
		}

		exp_list.editExpenditure(id, title, amount, category);
		exp_list.displayExpenditureList();
	}

	static void editExpenditure() {
		int id = -1;
		boolean valid = false;
		while (valid == false) {
			System.out.println("Please Enter the ID to Edit: ");
			Scanner input = new Scanner(System.in);
			try {
				id = input.nextInt();
				if (id >= 0 && id < exp_list.getCount()) {
					Expenditure exp = exp_list.getByIndex(id);
					System.out.println("Selected Expenditure: ");
					exp.displayExpenditure();
					updateExpenditure(id, exp);
					valid = true;
				} else {
					System.out.println("Invalid Input! ");
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}
	}

	static void deleteExpenditure() {
		int id = -1;
		boolean valid = false;
		while (valid == false) {
			System.out.println("Please Enter the ID to Delete: ");
			Scanner input = new Scanner(System.in);
			try {
				id = input.nextInt();
				if (id >= 0 && id < exp_list.getCount()) {
					valid = true;
				} else {
					System.out.println("Invalid Input! ");
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}

		Expenditure expToDelete = exp_list.getByIndex(id);
		exp_list.deleteExpenditureByID(id);
		System.out.println("Expenditure Deleted: ");
		expToDelete.displayExpenditure();
		System.out.println("Expenditure List: ");
		exp_list.displayExpenditureList();
	}

	static void menu() {
		int selection;
		Scanner input = new Scanner(System.in);

		/***************************************************/

		System.out.println("Choose from these choices");
		System.out.println("-------------------------\n");
		System.out.println("1 - Add Expense");
		System.out.println("2 - Edit Expense");
		System.out.println("3 - Delete Exppense");
		System.out.println("4 - Show All Expenses");
		System.out.println("5 - Quit");
		System.out.println("\n");

		selection = input.nextInt();

		switch (selection) {
			case 1:
				System.out.println("\n");
				System.out.println("Add Expense");
				System.out.println("-------------------------\n");
				addExpenditure();
				break;
			case 2:
				editExpenditure();
				break;
			case 3:
				deleteExpenditure();
				break;
			case 4:
				System.out.println("4 - Show All Expenses");
				exp_list.displayExpenditureList();
				break;
			case 5:
				System.out.println("Thank you! The App has been closed. ");
				running = false;
				break;
			default:
				System.out.println("Invalide Selection ");
				break;
		}
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
		exp_list.deleteExpenditure(exp3);

		// Display all expenditures
		exp_list.displayExpenditureList();

		// editing exp2
		exp_list.editExpenditure(1, "Bath supplies", 5.00, "Household");
		System.out.println("-------------------------------------------------------------");
		exp_list.displayExpenditureList();

		while (running) {
			menu();
		}

	}

}
