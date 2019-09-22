package company.gs;

import java.util.List;

public class TravellerFund {

  public static int requiredAmountAtStart(List<Integer> netSaving) {
    int leastSavingOnAnyDay = 0;
    if(netSaving.size()>0){
      leastSavingOnAnyDay = netSaving.get(0);
    }
    Integer netSavingOnNthDayFromStart = 0;
    for(int i=0; i<netSaving.size(); i++){
      netSavingOnNthDayFromStart = netSavingOnNthDayFromStart+netSaving.get(i);
      if(leastSavingOnAnyDay >= netSavingOnNthDayFromStart){
        leastSavingOnAnyDay = netSavingOnNthDayFromStart;
      }
    }
    if(leastSavingOnAnyDay>=0){
      return 0;
    } else{
      return (-1)*leastSavingOnAnyDay;
    }

  }

  public static void main(String[] args) {

  }
}
