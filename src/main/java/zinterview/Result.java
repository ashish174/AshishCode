package zinterview;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {


    public static int minDeletions(List<Integer> arr) {
        // Write your code here

        int n = arr.size();

        int lis = 0;
        int[] dp = new int[n+1];
        for (int i = 0; i < n; ++i) {
            int lo = 1;
            int hi = lis;
            while (lo <= hi) {
                int mid = lo + (hi-lo)/2;
                if (arr.get(dp[mid]) < arr.get(i)) {
                    lo = mid+1;
                } else {
                    hi = mid-1;
                }
            }
            int lis_here = lo;
            dp[lis_here] = i;

            lis = Math.max(lis, lis_here);
        }

        int res = Math.max(0, n-lis-1);
        return res;

    }


}
