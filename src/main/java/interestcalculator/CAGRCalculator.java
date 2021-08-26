package interestcalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CAGRCalculator {
    public static final Logger LOGGER = LoggerFactory.getLogger(CAGRCalculator.class);
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static NumberFormat rupeeFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));


    public static void main(String[] args) {
        double monthlyOrYearlyDeposit = 10000;
        boolean isMonthlyDeposit = true;
        int numOfYears = 7;
        double finalAmount = 1400000;
        double cagr = calculateInterest(monthlyOrYearlyDeposit, finalAmount, numOfYears, isMonthlyDeposit);
        LOGGER.info("Calculating:-");
        LOGGER.info("Period {} FinalAmount {} for monthlyOrYearlyDeposit deposit {}, Total final value {}", numOfYears, finalAmount, monthlyOrYearlyDeposit, rupeeFormat.format(finalAmount));

    }

    private static double calculateInterest(double monthlyOrYearlyAmount, double finalAmount, int numOfYears, boolean isMonthlyDeposit) {

        double finalAmountWithInterest = 0;
        for (int i = 0; i < numOfYears; i++) {
            double currYearInstallment;
            currYearInstallment = isMonthlyDeposit ? monthlyOrYearlyAmount * 12 : monthlyOrYearlyAmount;
            int currYearInstallmentTotalPeriod = numOfYears - i;
            // x = Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod) = A/P
            //int x = currYearInstallmentFinalValueWithInterest/currYearInstallment;
            int rate = 0;
            double currYearInstallmentFinalValueWithInterest = currYearInstallment * (Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod));

            finalAmountWithInterest += currYearInstallmentFinalValueWithInterest;
            LOGGER.info("Year {}  Period {} Installment amount {} , Total Final Value {}", i + 1, currYearInstallmentTotalPeriod, currYearInstallment, rupeeFormat.format(currYearInstallmentFinalValueWithInterest));
        }
        //System.out.printf("Final Amount : %.2f\n", finalAmountWithInterest);
        return finalAmountWithInterest;
    }
}
