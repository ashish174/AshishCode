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
        double monthlyDeposit = 1250;
        int numOfYears = 43;
        double annualInterest = 20.0;
        double finalAmount = calculateInterest(monthlyDeposit, annualInterest, numOfYears);
        LOGGER.info("Calculating:-");
        LOGGER.info("Period {} Rate {} for monthly deposit {}, Total final value {}", numOfYears, annualInterest, monthlyDeposit, rupeeFormat.format(finalAmount));

    }

    private static double calculateInterest(double amountPerMonth, double rate, int numOfYears) {
        double finalAmountWithInterest = 0;
        for (int i = 0; i < numOfYears; i++) {
            double currYearInstallment = amountPerMonth * 12;
            int currYearInstallmentTotalPeriod = numOfYears - i;
            double currYearInstallmentFinalValueWithInterest = currYearInstallment * (Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod));
            finalAmountWithInterest += currYearInstallmentFinalValueWithInterest;
            LOGGER.info("Year {}  Period {} Installment amount {} , Total Final Value {}", i+1, currYearInstallmentTotalPeriod, currYearInstallment, df2.format(currYearInstallmentFinalValueWithInterest));
        }
        //System.out.printf("Final Amount : %.2f\n", finalAmountWithInterest);
        return finalAmountWithInterest;
    }



}
