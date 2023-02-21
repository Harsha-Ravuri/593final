
/**
 * 
 */
import java.util.*;
import java.util.Locale.Category;
import java.util.stream.Collectors;

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
		Map<String, Double> totalSum = expenditure_list.stream()
				.collect(Collectors.groupingBy(Expenditure::getCategory,
						Collectors.summingDouble(Expenditure::getAmount)));
		System.out.println(totalSum);

	}

	// loop through the list of current expenditures
	// compare it with the start date
	// check if its equal or +7 than the start date
	// if it is display else loop over the next item in the list.

	public void displayExpenditureListBySelectedWeek(String strStartDate) {

		try {
			Date startDate = new SimpleDateFormat("MM-dd-yyyy").parse(strStartDate);
			boolean expensePresent = false;
			for (int i = 0; i < expenditure_count; i++) {
				boolean show = false;
				Date currentExpenseDate = expenditure_list.get(i).getDate();
				long diffInMillies = Math.abs(currentExpenseDate.getTime() - startDate.getTime());
				long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

				if(currentExpenseDate.compareTo(startDate) > 0 && diff < 8) {
					show = true;
				}
				else if(currentExpenseDate.compareTo(startDate) == 0) {
					show = true;
				}
				else
					show = false;
				if(show) {
					expensePresent = true;
					System.out.println("ID: " + i);
					expenditure_list.get(i).displayExpenditure();
				}
			}
			if(!expensePresent){
				System.out.println("No expenses in the given week");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

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
