import java.util.ArrayList;

public class BudgetServices {

    ArrayList<Double> currentExpenditures = new ArrayList<>();
    double weeklyBudget = 0;
    double monthlyBudget = 0;

    void addToCurrentExpenditure(double newExpense){
        currentExpenditures.add(newExpense);
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


}
