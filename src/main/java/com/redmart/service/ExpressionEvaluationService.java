package com.redmart.service;

import java.text.DecimalFormat;
import java.util.Stack;
import java.util.logging.Logger;

public class ExpressionEvaluationService {

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

