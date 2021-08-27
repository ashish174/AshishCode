package algo.practice.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RearrangeZero {
  Logger logger = LoggerFactory.getLogger(RearrangeZero.class);
  public static void main(String[] args) {
    int[] arr = {1,2,0,0,0,3,6};
    //int[] arr = {1,2,3,2,1,5,7};
    new RearrangeZero().rearrangeZero(arr);
  }

  void rearrangeZero(int[] arr){
    logger.info("{}", arr);
    if(arr==null || arr.length==0){
      logger.info("Array Underflow");
      return;
    }
    int numCount = -1;
    int tmp;
    for (int i = 0; i < arr.length; i++) {
      if(arr[i]>0){
        numCount++;
        arr[numCount] = arr[i];
        if(numCount!=i){
          arr[i]=0;
        }

      }
    }
    logger.info("{}", arr);
  }
}
