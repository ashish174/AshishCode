package com.redmart.service;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class MathOperations {
  public static void main(String[] args) throws IOException {
    //System.out.println(ExpressionEvaluationService.evaluateExpression("23*211++*"));
    String line;
    //Scanner scanner = new Scanner(System.in);
    BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
    while((line = inputStream.readLine()) !=null && line.length()!=0){
      System.out.println(evaluateExpression(line));
    }

  }

  public static float evaluateExpression(String input){
    if(!validateInput(input))
      return -1;
    Stack<Float> stack = new Stack<Float>();
    float num, num1, num2;
    float sum = 0.000f;
    for(char ch : input.toCharArray()){
      if(Character.isDigit(ch)){
        num = Character.getNumericValue(ch);

        stack.push(num);
      }
      else{
        if(stack.size()<2){
          return -1;
        }
        num2 = stack.pop();
        num1 = stack.pop();
        System.out.println("Parsing :"+num1+""+ch+""+num2);
        switch(ch) {
          case '/': sum = num1 / num2; break;
          case '*': sum = num1 * num2; break;
          case '+': sum = num1 + num2; break;
          case '-': sum = num1 - num2; break;
          default: break; /* invalid op? */
        }
        //DecimalFormat df = new DecimalFormat("#.###");
        stack.push(sum);
      }


    }
    if(stack.size()==1){
      return sum;
    }
    return -1;
  }

  static boolean  validateInput(String input){
    if (input==null)
      return false;
    //return input.matches("");
    return true;
  }
}
