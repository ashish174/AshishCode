package com.redmart.service.Samples;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
  public static void main(String[] args) {
    List<Object> objlist = new ArrayList<>();
    if(objlist.isEmpty()){
      System.out.println("Empty");
    } else{
      System.out.println("Not Empty");
    }
  }
}
