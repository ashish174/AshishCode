package com.redmart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Sample1 {
  public static <T> String getCommaSeparatedString(List<T> inputSet){
    if (inputSet != null && !inputSet.isEmpty()) {

      Optional<String> optional = Optional.of(inputSet.stream().filter(Objects::nonNull).map(String::valueOf)
          .collect(Collectors.joining(",")));

      if (optional.isPresent()) {

        return optional.get();
      }
    }

    return null;
  }

  public static void main(String[] args) {
    List<String> contracts = Arrays.asList("abc", "def", "ghi");
    System.out.println(contracts);
    System.out.println(getCommaSeparatedString(contracts));

  }
}
