package company.sorocco;

import java.util.Scanner;

public class Permutation {


  public static void main(String[] args) {
    System.out.println("Enter the String");
    Scanner sc = new Scanner(System.in);
    String inputNum=sc.nextLine();
    int permutationNum = 0;
    if(inputNum.length()==1 && inputNum.charAt(0)=='0'){
      permutationNum = 0;
    } else{
      permutationNum = 1 + getPermuteForPair(inputNum);
    }
    System.out.println("Permutaon for "+inputNum+" :- "+permutationNum);
  }

  public static int getPermuteForPair(String inputNum){
    int size = inputNum.length();
    if(size==0)
      return 0;
    if(size==1){
      return 0;
    }
    if(size >= 2){
      int digit1 = Character.getNumericValue(inputNum.charAt(0));
      int digit2 = Character.getNumericValue(inputNum.charAt(1));
      if((digit1 == 0)||(digit1>2))
        return getPermuteForPair(inputNum.substring(1));
      else if((digit1>0) && (digit1<3)){
        if((digit1==2)&&(digit2 > 6)){
          return getPermuteForPair(inputNum.substring(1));
        }else {
          return 1 + getPermuteForPair(inputNum.substring(1))+getPermuteForPair(inputNum.substring(2));
        }
      }

    }
    return 0;
  }

  public static int getPermuteForSingle(String inputNum){
    if(inputNum.indexOf('0')==-1)
      return 1;
    else
      return 0;
  }

}



