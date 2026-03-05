package algo.practice.a_common_code_functions.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * THis is for reading input from a console(using keyboard)
 * 1️⃣ Scanner (simple, safe for interviews)
 * 2️⃣ BufferedReader + StringTokenizer (faster for competitive programming)
 *
 * So Scanner is usually enough, unless they explicitly mention performance.
 *
 */
public class KeyboardInput {

    void scannerSample(){
        Scanner sc = new Scanner(System.in);

        // Read single integer
        int n = sc.nextInt();

        // Read array
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        // Example: print array
        for(int x : arr){
            System.out.print(x + " ");
        }



        //Reading whole line(strings)
        String s = sc.nextLine();
        System.out.println(s);

        sc.close();
    }

    /**
     * used for large input constraints
     */
    void bufferedReaderSample() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");

        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(input[i]);
        }

        br.close();

    }

    public static void main(String[] args) {


    }

}
