package miscellenous;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TestClass {
  public static void main(String[] args) throws IOException {
    String s = "baby";
    System.out.println(s);
    System.out.println(CountOccurrences(s));
  }

  static String CountOccurrences(String S){
    // Your code goes here
    int[] charArray = new int[26];
    for(int i =0; i < S.length(); i++){
      char ch = S.charAt(i);
      int index = ch - 97;
      charArray[index]++;
    }


    StringBuilder newString = new StringBuilder();
    for(int i =0; i < S.length(); i++){
      char ch = S.charAt(i);
      int index = ch - 97;
      if(charArray[index]!=0){
        newString.append(ch);
        newString.append(charArray[index]);
        charArray[index]=0;
      }
    }
    return newString.toString();



  }
}