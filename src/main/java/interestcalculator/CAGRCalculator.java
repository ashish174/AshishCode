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
        // Calculate CAGR based on expected final value and SIP amount and num of years for SIP
        LOGGER.info("Calculate CAGR based on expected final value and SIP amount and num of years for SIP");
        double monthlyOrYearlyDeposit = 10000;
        boolean isMonthlyDeposit = true;
        int numOfYears = 7;
        double finalAmount = 1400000;
        // This is a wrong implementation
        double expectedCagr = calculateInterest(
                monthlyOrYearlyDeposit,
                finalAmount,
                numOfYears,
                isMonthlyDeposit);

        LOGGER.info("\n \n");

        // Calculate Final Amount based on SIP amount, CAGR and num of years for SIP
        LOGGER.info("Calculate Final Amount based on SIP amount, CAGR and num of years for SIP");
        double monthlyOrYearlyDeposit1 = 100000;
        boolean isMonthlyDeposit1 = true;
        int numOfYears1 = 5;
        double cagr1 = 12;
        double finalAmount1 = calculateFinalAmount(
                monthlyOrYearlyDeposit1,
                cagr1,
                numOfYears1,
                isMonthlyDeposit1);


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
        double finalAmountWithInterest = 0;
        for (int i = 0; i < numOfYears; i++) {
            double currYearInstallment;
            currYearInstallment = isMonthlyDeposit ? monthlyOrYearlyAmount * 12 : monthlyOrYearlyAmount;
            int currYearInstallmentTotalPeriod = numOfYears - i;
            // A = P * Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod)
            int rate = 0;
            double currYearInstallmentFinalValueWithInterest = currYearInstallment * (Math.pow((1 + rate / 100.0), currYearInstallmentTotalPeriod));


            finalAmountWithInterest += currYearInstallmentFinalValueWithInterest;
            LOGGER.info("Year {}  Period {} Installment amount {} , Total Final Value {}", i + 1, currYearInstallmentTotalPeriod, currYearInstallment, rupeeFormat.format(currYearInstallmentFinalValueWithInterest));
        }
        //System.out.printf("Final Amount : %.2f\n", finalAmountWithInterest);
        return finalAmountWithInterest;
    }

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
        LOGGER.info("SIP Amount {}, SIPType {}, CAGR {}, NumOfYears {} -> \n \t \t \t Total FinalAmount {}"
                , monthlyOrYearlyAmount
                , isMonthlyDeposit?"Monthly":"Yearly"
                , cagr
                , numOfYears
                , rupeeFormat.format(finalAmountWithInterest));
        return finalAmountWithInterest;
    }
}
