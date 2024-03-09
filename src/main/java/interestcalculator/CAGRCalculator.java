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
        // Calculate Final Amount based on SIP amount, CAGR and num of years for SIP
        LOGGER.info("Calculate Final Amount based on SIP amount, CAGR and num of years for SIP");
        double monthlyOrYearlyDeposit1 = 100000;
        boolean isMonthlyDeposit1 = true;
        int numOfYears1 = 10;
        double cagr1 = 12;
        double finalAmount1 = calculateFinalAmount(
                monthlyOrYearlyDeposit1,
                cagr1,
                numOfYears1,
                isMonthlyDeposit1);
    }


    /**
     * Calculate Final Amount reached for monthlyOrYearlyAmount/SIP for r cagr in x numOfyears
     * @param monthlyOrYearlyAmount
     * @param cagr
     * @param numOfYears
     * @param isMonthlyDeposit
     * @return
     */
    private static double calculateFinalAmount(double monthlyOrYearlyAmount, double cagr, int numOfYears, boolean isMonthlyDeposit) {
        double finalAmountWithInterest = 0;
        for (int i = 0; i < numOfYears; i++) {
            double currYearInstallment = isMonthlyDeposit
                    ? monthlyOrYearlyAmount * 12
                    : monthlyOrYearlyAmount;
            int currYearInstallmentTotalPeriod = numOfYears - i;
            double currYearInstallmentFinalValueWithInterest = currYearInstallment * Math.pow((1 + cagr / 100.0), currYearInstallmentTotalPeriod);
            finalAmountWithInterest += currYearInstallmentFinalValueWithInterest;
            LOGGER.info("Year {}  Period {} Installment amount {} , Total Final Value {}", i + 1, currYearInstallmentTotalPeriod, currYearInstallment, rupeeFormat.format(currYearInstallmentFinalValueWithInterest));
        }
        LOGGER.info("SIP Amount {}, SIP Type {}, CAGR {}, NumOfYears {} -> \n \t \t \t Total FinalAmount {}"
                , monthlyOrYearlyAmount
                , isMonthlyDeposit?"MONTHLY":"YEARLY"
                , cagr
                , numOfYears
                , rupeeFormat.format(finalAmountWithInterest));
        return finalAmountWithInterest;
    }

    /**
     *
     * Calculate monthlyOrYearly SIP required to reach a final amount with r CAGR in x numOfyears
     * @param finalAmount
     * @param cagr
     * @param numOfYears
     * @param isMonthlyDeposit
     * @return
     */
    private static double calculateSIP(double finalAmount, double cagr, int numOfYears, boolean isMonthlyDeposit) {
        // Summation is of form Geometric Series : ar^0 + ar^1 + ar^2 + ar^3 + ...ar^n-1 = a(1-r^n)/(1-r)
        double finalAmountWithInterest = 0;
        for (int i = 0; i < numOfYears; i++) {
            // For each year
            // currYearInstallmentTotalPeriod = numOfYears - i
            // A = P * Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod)
        }
        return 0;
    }

    /**
     * This is a wrong implementation
     *
     * Calculate CAGR required for monthlyOrYearlyAmount/SIP to reach a final amount in x numOfyears
     * @param monthlyOrYearlyAmount
     * @param finalAmount
     * @param numOfYears
     * @param isMonthlyDeposit
     * @return
     */
    private static double calculateInterest(double monthlyOrYearlyAmount, double finalAmount, int numOfYears, boolean isMonthlyDeposit) {
        // This is a wrong implementation
        // Summation is of form Geometric Series : ar^0 + ar^1 + ar^2 + ar^3 + ...ar^n-1 = a(1-r^n)/(1-r)
        double finalAmountWithInterest = 0;
        double currYearInstallment = isMonthlyDeposit ? monthlyOrYearlyAmount * 12 : monthlyOrYearlyAmount;
        // For each year
        // currYearInstallmentTotalPeriod = numOfYears - i
        // A = P * Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod)

        // Use Geometric Summation : ar^0 + ar^1 + ar^2 + ar^3 + ...ar^n-1 = a(1-r^n)/(1-r)
        // Final Amount = currYearInstallment
        // double finalAmount/currYearInstallment*(r^0 + r^1 + r^2 + r^3 + ...r^n-1)


        return 0;
    }
}
