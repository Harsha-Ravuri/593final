import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BudgetServices {

    ArrayList<Double> currentExpenditures = new ArrayList<>();
    double weeklyBudget = 0;
    double monthlyBudget = 0;
    Map<String,Double> categoryBudget = new HashMap<>();
    Map<String,Double> currentExpensesMap = new HashMap<>();

    void addToCurrentExpenditure(double newExpense){
        currentExpenditures.add(newExpense);
    }

    void addToCurrentExpenditureCategory(String category, double newExpense){
        currentExpensesMap.put(category,currentExpensesMap.getOrDefault(category,0.0)+newExpense);
    }

    void setWeeklyBudget(double newWeeklyBudget){
        weeklyBudget = newWeeklyBudget;
        calculateMonthlyBudget();
    }

    double getCurrentExpenditure(){
        double totalSum = 0.0;
        for(double d : currentExpenditures)
            totalSum = totalSum + d;
        return totalSum;
    }

    double getMonthlyBudget(){
        double totalSum = 0.0;
        for(double d : currentExpenditures)
            totalSum = totalSum + d;
        return totalSum * 4;
    }

    void setBudgetCategory(String category, double budget){
        categoryBudget.put(category,budget);
    }

    void calculateMonthlyBudget(){
        monthlyBudget = weeklyBudget * 4;
    }

    String predictMonthlyBudget(){
        String result = "";
        double allExpensesTillNow = 0;
        for(double val : currentExpenditures)
            allExpensesTillNow = allExpensesTillNow + val;

        double predictedMonthlyExpense = allExpensesTillNow * 4;
        if(predictedMonthlyExpense > monthlyBudget)
            result = "Based on current spending, you'll' be over budget for this month";
        else if(predictedMonthlyExpense == monthlyBudget)
            result = "Based on current spending, you'll be spending exactly as your set budget";
        else
            result ="You'll be under budget this month";

        return result;
    }

    String predictMonthlyCategoryBudget(String category){
        String result = "Nothing to display here!";
        double currentExpenses;
        if(currentExpensesMap.containsKey(category)) { // if i have spent on food
            currentExpenses = currentExpensesMap.get(category);
            if(currentExpenses == 0.0)
                result = "No expenses in this category yet";
            else
            {
                if(categoryBudget.containsKey(category)) { // if i have budget for food
                    double weeklyBudget = categoryBudget.get(category);
                    double monthlyBudget = weeklyBudget * 4;
                    double predictedMonthlyExpenses = currentExpenses * 4;
                    if(predictedMonthlyExpenses > monthlyBudget)
                        result = "Based on current spending, you'll' be over budget for this month";
                    else if(predictedMonthlyExpenses == monthlyBudget)
                        result = "Based on current spending, you'll be spending exactly as your set budget";
                    else
                        result ="You'll be under budget this month";
                }
                else
                    result ="No budget set for this category yet";
            }
        }
        else
            result = "No expenses in this category yet";
        return result;
    }


}
