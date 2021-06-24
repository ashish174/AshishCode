package interestcalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonthlyDepositCompoundInterestCalculator {
    public static final Logger LOGGER = LoggerFactory.getLogger(MonthlyDepositCompoundInterestCalculator.class);
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static NumberFormat rupeeFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));


    public static void main(String[] args) {
        double monthlyOrYearlyDeposit = 52044;
        boolean isMonthlyDeposit = false;
        int numOfYears = 22;
        double annualInterest = 2.0;
        double finalAmount = calculateInterest(monthlyOrYearlyDeposit, annualInterest, numOfYears, isMonthlyDeposit);
        LOGGER.info("Calculating:-");
        LOGGER.info("Period {} Rate {} for monthlyOrYearlyDeposit deposit {}, Total final value {}", numOfYears, annualInterest, monthlyOrYearlyDeposit, rupeeFormat.format(finalAmount));

    }

    private static double calculateInterest(double monthlyOrYearlyAmount, double rate, int numOfYears, boolean isMonthlyDeposit) {
        double finalAmountWithInterest = 0;
        for (int i = 0; i < numOfYears; i++) {
            double currYearInstallment = monthlyOrYearlyAmount;
            currYearInstallment = isMonthlyDeposit ? monthlyOrYearlyAmount * 12 : monthlyOrYearlyAmount;
            int currYearInstallmentTotalPeriod = numOfYears - i;
            double currYearInstallmentFinalValueWithInterest = currYearInstallment * (Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod));
            finalAmountWithInterest += currYearInstallmentFinalValueWithInterest;
            LOGGER.info("Year {}  Period {} Installment amount {} , Total Final Value {}", i + 1, currYearInstallmentTotalPeriod, currYearInstallment, rupeeFormat.format(currYearInstallmentFinalValueWithInterest));
        }
        //System.out.printf("Final Amount : %.2f\n", finalAmountWithInterest);
        return finalAmountWithInterest;
    }


}
