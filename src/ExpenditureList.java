
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

	public void editExpenditure(int idx, String title, double amount, String category) {
		Expenditure this_exp = expenditure_list.get(idx);
		this_exp.title = title;
		this_exp.amount = amount;
		this_exp.category = category;
	}

	public void displayExpenditureList() {
		System.out.println("\n");
		for (int i = 0; i < expenditure_count; i++) {

			System.out.println("ID: " + i);
			expenditure_list.get(i).displayExpenditure();
			System.out.println("\n");
		}

		System.out.println("Total Expenditure : " + getTotalAmount());
		System.out.println("\n");
	}

	public int getCount() {
		return expenditure_count;
	}

	public Expenditure getByIndex(int idx) {
		return expenditure_list.get(idx);
	}

	public List<Expenditure> getExpenditureList() {
		return expenditure_list;
	}

	public double getTotalAmount() {
		double sum = 0;
		for (int i = 0; i < expenditure_count; i++) {
			Expenditure exp = expenditure_list.get(i);
			sum += exp.getAmount();
		}

		return sum;
	}

}
