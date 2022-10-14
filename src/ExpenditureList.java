
/**
 * 
 */
import java.util.*;

/**
 * @author Harsha Ravuri
 *
 */
public class ExpenditureList {
	private int expenditure_count;
	private List<Expenditure> expenditure_list = new ArrayList<Expenditure>();

	public ExpenditureList() {
	}

	public void addExpenditure(Expenditure exp) {
		expenditure_list.add(exp);
		expenditure_count++;
	}

	public void deleteExpenditure(Expenditure exp) {
		expenditure_list.remove(exp);
		--expenditure_count;
	}

	public void deleteExpenditureByID(int idx) {
		expenditure_list.remove(idx);
		--expenditure_count;
	}

	public void editExpenditure(Expenditure exp, String title, double amount, String category) {
		int this_exp_index = expenditure_list.indexOf(exp);
		Expenditure this_exp = expenditure_list.get(this_exp_index);
		this_exp.title = title;
		this_exp.amount = amount;
		this_exp.category = category;
	}

	public void displayExpenditureList() {
		System.out.println("\n");
		System.out.println("-------------------------\n");
		for (int i = 0; i < expenditure_count; i++) {

			System.out.println("ID: " + i);
			expenditure_list.get(i).displayExpenditure();
			System.out.println("\n");
		}
	}

	public int getCount() {
		return expenditure_count;
	}

}
