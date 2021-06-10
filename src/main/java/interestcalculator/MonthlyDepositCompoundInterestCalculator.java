package interestcalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonthlyDepositCompoundInterestCalculator {
    public static final Logger LOGGER = LoggerFactory.getLogger(MonthlyDepositCompoundInterestCalculator.class);

    public static void main(String[] args) {
        double monthlyDeposit = 1250;
        int numOfYears = 43;
        double annualInterest = 20.0;
        double finalAmount = calculateInterest(monthlyDeposit, annualInterest, numOfYears);
        LOGGER.info("Calculating:-");
        LOGGER.info("Period {} Rate {} for monthly deposit {}, Total final value {}", numOfYears, annualInterest, monthlyDeposit, finalAmount);


    }

    private static double calculateInterest(double amountPerMonth, double rate, int numOfYears) {
        double finalAmountWithInterest = 0;
        for (int i = 0; i < numOfYears; i++) {
            double currYearInstallment = amountPerMonth * 12;
            int currYearInstallmentTotalPeriod = numOfYears - i;
            double currYearInstallmentFinalValueWithInterest = currYearInstallment * (Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod));
            finalAmountWithInterest += currYearInstallmentFinalValueWithInterest;
            LOGGER.info("Year {}  Period {} Installment amount {} , Total Final Value {}", i+1, currYearInstallmentTotalPeriod, currYearInstallment, currYearInstallmentFinalValueWithInterest);
        }
        System.out.printf("Final Amount : %f\n", finalAmountWithInterest);
        return finalAmountWithInterest;
    }

}
