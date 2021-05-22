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
public class CoinChangeByGreedy {
    public static final Logger logger = LoggerFactory.getLogger(CoinChangeByGreedy.class);


    public static Map<Integer, Integer> findChangeCoins(int[] deno, int amount) {
        int amountRemainingToChange = amount;
        Map<Integer, Integer> coinChangeMap = new HashMap<>();
        int i = 0;
        while (i < deno.length) {
            int currDeno = deno[deno.length - 1 - i];
            if (currDeno <= amountRemainingToChange) {
                amountRemainingToChange = amountRemainingToChange - currDeno;
                coinChangeMap.put(currDeno, coinChangeMap.getOrDefault(currDeno, 0) + 1);
                if (amountRemainingToChange == 0) {
                    break;
                }
            } else {
                i++;
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
        //int deno[] = {50, 60,  100, 500, 1000}; Amount: 610 - For this case we have to use backtracking/dp
        findChangeCoins(deno, 610);
    }
}
