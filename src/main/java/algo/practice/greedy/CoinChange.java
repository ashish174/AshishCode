package algo.practice.greedy;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * we have an infinite supply of each of the denominations
 * what is the minimum number of coins and/or notes needed to make the change?
 * Ex: Denominations :  { 1, 2, 5, 10, 20, 50, 100, 500, 1000}
 */
public class CoinChange {
    public static Logger logger = LoggerFactory.getLogger(CoinChange.class);


    public static Map<Integer, Integer> findChangeCoins(int[] deno, int amount) {
        int amountRemainingToChange = amount;
        Map<Integer, Integer> coinChangeMap = new HashMap<>();
        int i = 0;
        while (i < deno.length) {
            int curr_deno = deno[deno.length - 1 - i];
            if (curr_deno > amountRemainingToChange) {
                i++;
                continue;
            } else {
                amountRemainingToChange = amountRemainingToChange - curr_deno;
                coinChangeMap.put(curr_deno, coinChangeMap.getOrDefault(curr_deno, 0) + 1);
                if (amountRemainingToChange == 0) {
                    break;
                }
            }

        }
        if (amountRemainingToChange == 0) {
            logger.info("Coin Change for {} can be made with given denomination : {}", amount, deno);
            logger.info("Coin Change for {} : {}", amount, coinChangeMap);
            return coinChangeMap;
        }
        logger.error("Coin Change for {} can not be made with given denomination : {}", amount, deno);
        return null;

    }

    public static void main(String[] args) {
        //int deno[] = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
        int[] deno = {10, 20, 50, 100, 500, 1000};
        //int deno[] = {50, 60,  100, 500, 1000}; - For this case we have to use backtracking
        findChangeCoins(deno, 610);
    }
}
