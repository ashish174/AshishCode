package miscellenous;

import java.util.HashMap;
    import java.util.Scanner;
    import java.util.StringJoiner;
    public class IntToHexDecimal {
        static char hexaChar[] = {'F','E','D','C','B','A'};
        static String findHexa(int no){
            String result =""; //variable to store final result
            int reminder ; //variable to store reminder
            while(no>0){
                reminder = no % 16;
                if(reminder > 9){
                    result = hexaChar[15 - reminder] + result;
                }else{
                    result = reminder+result;
                }
                no = no/16;
            }
        return result;
        }
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a number ");
            int num = scanner.nextInt();
            System.out.println("Hexa "+findHexa(num));
        }
    }