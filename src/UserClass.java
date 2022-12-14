import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Locale.Category;

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
	static String[] categoryArray = new String[] { "Food", "Clothing", "Transportation", "Entertainment", "Other" };
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

	static void printCategory() {
		System.out.println("Category Options: \n");
		for (int i = 0; i < categoryArray.length; i++) {
			System.out.println(i + " - " + categoryArray[i]);
		}
	}

	static String chooseCategory() {
		int selection;
		String category = "";

		System.out.println("\n");
		System.out.println("Please Select the Category: ");
		printCategory();
		boolean valid = false;
		while (!valid) {
			Scanner input = new Scanner(System.in);
			try {
				selection = input.nextInt();
				if (selection >= 0 && selection < categoryArray.length) {
					valid = true;
					category = categoryArray[selection];
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}

		return category;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	static String chooseCategoryForEdit() {
		int selection;
		String category = "";

		System.out.println("\n");
		System.out.println("Please Select the Category (Press ENTER to Skip): ");
		printCategory();
		boolean valid = false;
		while (!valid) {
			Scanner input = new Scanner(System.in);
			try {
				String user_input = input.next();
				if(user_input.isEmpty()) {
					System.out.println("Cannot be empty. Try again");
					continue;
				}
				else if(!(isNumeric(user_input))) {
					System.out.println("Please enter valid option");
					continue;	
				}
				else{
					selection = Integer.parseInt(user_input);
					if (selection >= 0 && selection < categoryArray.length) {
						valid = true;
						category = categoryArray[selection];
					} 
					else
						throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}

		return category;
	}

	static void addExpenditure() {
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

		String category = chooseCategory();
		Expenditure exp = new Expenditure(title, amount, currentDate, category);
		exp_list.addExpenditure(exp);
		exp_list.displayExpenditureList();
	}

	static void updateExpenditure(int id, Expenditure exp) {
		Scanner input = new Scanner(System.in);
		String title = exp.getTitle();
		double amount = exp.getAmount();
		String category = exp.getCategory();

		System.out.println("Please Enter The Title (Press ENTER to Skip): ");
		String title_entered = input.nextLine();
		if (!title_entered.isEmpty()) {
			title = title_entered;
		}

		boolean valid = false;
		while (valid == false) {
			System.out.println("Please Enter The Amount (Press ENTER to Skip): ");
			Scanner newInput = new Scanner(System.in);
			String amount_entered = newInput.nextLine();
			if (amount_entered.isEmpty()) {
				break;
			}
			try {
				Double amount_entered_as_double = Double.parseDouble(amount_entered);
				amount = round(amount_entered_as_double, 2);
				if (amount >= 0 && amount <= 1000000) {
					valid = true;
				} else {
					System.out.println("Invalid Input! ");
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}

		String newCategory = chooseCategoryForEdit();
		if (newCategory != "") {
			category = newCategory;
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
				String user_input = input.next();
				if(user_input.isEmpty()) {
					System.out.println("Cannot be empty.");
					continue;
				}
				else if(isNumeric(user_input)==false) {
					System.out.println("Please enter valid option");
					continue;	
				}	
				else {
					id = Integer.parseInt(user_input);
					System.out.println("id is "+id);
					if (id >= 0 && id < exp_list.getCount()) {
						Expenditure exp = exp_list.getByIndex(id);
						System.out.println("Selected Expenditure: ");
						exp.displayExpenditure();
						updateExpenditure(id, exp);
						valid = true;
					} else {
						System.out.println("Invalid Input! ");
					}	
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
		
		String user_option = input.next();
		while(!(isNumeric(user_option))) {
			System.out.println("Please enter a valid choice!");
			user_option = input.next();
		}
		selection = Integer.parseInt(user_option);

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
				System.out.println("Invalid Selection. Please enter a valid selection ");
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
