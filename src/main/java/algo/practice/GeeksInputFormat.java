package algo.practice;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeeksInputFormat {
    public static void main(String[] args) {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        List<String> tokens = new ArrayList<>();
        int numOfTestCases = scanner.nextInt();
        if(numOfTestCases>0){
            while(numOfTestCases>0){
                int size = scanner.nextInt();
                String[] tokenStart = (scanner.nextLine().split(" "));
                String[] tokenEnd = (scanner.nextLine().split(" "));
                int[] startTime = new int[size];
                int[] endTime = new int[size];;
                for(int i = 0; i< size; i++){
                    startTime[i] = Integer.parseInt(tokenStart[i]);
                    endTime[i] = Integer.parseInt(tokenEnd[i]);
                }
                //Run Code for each input set
                numOfTestCases--;

            }
        }


        while(scanner.hasNextLine()){
            tokens.add(scanner.nextLine());
            System.out.println(tokens);
        }
        scanner.close();
        //System.out.println(tokens);


    }
}
