package algo.practice.a_common_code_functions.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is for reading input from a file
 *
 * 1️⃣ Scanner + File (simplest)
 * 2️⃣ BufferedReader + FileReader (faster)
 *
 *
 */
public class FileInput {
    void scannerSample() throws FileNotFoundException {
        //File file = new File("input.txt");
        //In java \ is a escape charcater, so we must use \\
        File file = new File("C:\\Users\\YourName\\Desktop\\input.txt");
        Scanner sc = new Scanner(file);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        for(int x : arr){
            System.out.print(x + " ");
        }

        sc.close();
    }

    void bufferedReaderSample() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(br.readLine());

        String[] parts = br.readLine().split(" ");

        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(parts[i]);
        }

        br.close();
    }
}
