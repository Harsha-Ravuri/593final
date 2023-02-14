import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

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
	static ArrayList<String> categoryArray = new ArrayList<>(Arrays.asList("Food", "Clothing", "Gas", "Entertainment", "Other" ));
	static String[] allowedCategoryArray = new String[] {"Medicine", "Pet", "Travel", "Miscellaneous"};
	static boolean running = true;
	static BudgetServices budgetServices;
	static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");


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
		for (int i = 0; i < categoryArray.size(); i++) {
			System.out.println(i+1 + " - " + categoryArray.get(i));
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
				if (selection > 0 && selection <= categoryArray.size()) {
					valid = true;
					category = categoryArray.get(selection-1);
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
					if (selection >= 0 && selection < categoryArray.size()) {
						valid = true;
						category = categoryArray.get(selection-1);
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
		double amount = 0.0;

		boolean valid = false;
		while (!valid) {
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

		System.out.println("Please enter the date in MM-dd-yyyy format: ");
		String date = "";
		boolean validDate = false;
		while (!validDate) {
			Scanner newInput = new Scanner(System.in);
			try {
				date = newInput.nextLine();
				if (matches(date)) {
					validDate = true;
				} else {
					System.out.println("Invalid Input! ");
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}

		String category = chooseCategory();
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("MM-dd-yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Expenditure exp = new Expenditure(title, amount, date1, category, date);
		exp_list.addExpenditure(exp);
		exp_list.displayExpenditureList();
		budgetServices.addToCurrentExpenditure(amount);
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
		System.out.println("3 - Delete Expense");
		System.out.println("4 - Show All Expenses");
		System.out.println("5 - Set budget for the week");
		System.out.println("6 - Predict my monthly expenditure behavior");
		System.out.println("7 - Show weekly expenses by selected week");
		System.out.println("8 - Add new category for expenses");
		System.out.println("9 - Quit");
		System.out.println("\n");

		String user_option = input.next();
		while(!(isNumeric(user_option))) {
			System.out.println("Please enter a valid choice!");
			user_option = input.next();
		}
		selection = Integer.parseInt(user_option);

		switch (selection) {
			case 1:
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
				System.out.println("5 - Please enter your budget for a week");
				setWeeklyBudget();
				break;
			case 6:
				System.out.println("Your monthly prediction with current spending behavior");
				predictMonthlyBudget();
				break;
			case 7:
				System.out.println("Please enter the start date for the week to show expenses in mm-dd-yyyy format");
				showWeeklyBudgetBySelection();
				break;
			case 8:
				System.out.println("Please enter the new category you'd like to add");
				addNewCategory();
				break;
			case 9:
				System.out.println("Thank you! The App has been closed. ");
				running = false;
				break;
			default:
				System.out.println("Invalid Selection. Please enter a valid selection ");
				break;
		}
	}

	private static void addNewCategory() {
		String newCategory = "";
		Scanner newInput = new Scanner(System.in);
		newCategory = newInput.nextLine();
		boolean added = false;

		for(String category : allowedCategoryArray){
			if(category.equalsIgnoreCase(newCategory)){
				categoryArray.add(newCategory);
				added = true;
				break;
			}
		}
		if(added)
			System.out.println("Category added successfully");
		else
			System.out.println("Our developers will review your request for the new category");

	}

	private static void showWeeklyBudgetBySelection() {
		String date = "";
		boolean validDate = false;
		while (!validDate) {
			Scanner newInput = new Scanner(System.in);
			try {
				date = newInput.nextLine();
				if (matches(date)) {
					validDate = true;
				} else {
					System.out.println("Invalid Input! ");
				}
			} catch (Exception e) {
				System.out.println("Invalid Input! ");
			}
		}

		exp_list.displayExpenditureListBySelectedWeek(date);
	}

	private static void predictMonthlyBudget() {
		System.out.println("Current expenditure "+ budgetServices.getCurrentExpenditure());
		System.out.println("Monthly prediction "+ budgetServices.getMonthlyBudget());
		String value = budgetServices.predictMonthlyBudget();
		System.out.println(value);
	}

	private static void setWeeklyBudget() {
		Scanner sc = new Scanner(System.in);
		int newBudget = sc.nextInt();
		budgetServices.setWeeklyBudget(newBudget);
	}

	public static boolean matches(String date) {
		return DATE_PATTERN.matcher(date).matches();
	}

	public static void main(String[] args) {

		// initializing budgetService object;
		budgetServices = new BudgetServices();

		// making new expenditure
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		Expenditure exp = new Expenditure("Potatoes", 5.00, now, "Food");
//
//		// making 2nd expenditure
//		LocalDateTime now2 = LocalDateTime.now();
//		Expenditure exp2 = new Expenditure("Oranges", 18.95, now, "Food");
//
//		// making 3rd expenditure
//		LocalDateTime now3 = LocalDateTime.now();
//		Expenditure exp3 = new Expenditure("Car Insurance", 400.00, now, "Transportation");
//
//		exp_list.addExpenditure(exp);
//		exp_list.addExpenditure(exp2);
//		exp_list.addExpenditure(exp3);
//		exp_list.deleteExpenditure(exp3);

		// Display all expenditures
//		exp_list.displayExpenditureList();
//
//		// editing exp2
//		exp_list.editExpenditure(1, "Bath supplies", 5.00, "Household");
//		System.out.println("-------------------------------------------------------------");
//		exp_list.displayExpenditureList();

		while (running) {
			menu();
		}

	}

}
