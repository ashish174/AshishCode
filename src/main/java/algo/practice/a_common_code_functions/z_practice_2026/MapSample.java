package algo.practice.a_common_code_functions.z_practice_2026;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class MapSample {


    public static void main(String[] args){
        //int[] = [price, year, amount]
        //sort by year, price (desc), amount
        Map<Integer, int[]> myMap = new HashMap<>();
        myMap.put(1, new int[]{1,2,3});
        myMap.put(2, new int[]{5,2,1});
        myMap.put(3, new int[]{3,1,1});
        myMap.put(4, new int[]{1,1,1});


        List<int[]> sortedList = myMap.values().stream()
                .sorted(Comparator
                        .comparing((int[] entry) -> entry[1])
                        .thenComparing(entry -> entry[0], Comparator.reverseOrder())
                        .thenComparing(entry -> entry[2])
                )
                .collect(Collectors.toList());

        log.info("Sorted List : {}", sortedList);
        for(int[] entry : sortedList){
            System.out.printf("%d : %d : %d \n", entry[1], entry[0], entry[2]);
        }

        List<Movie> movieList = new LinkedList<>();
        movieList.add(new Movie("GodZilla", 100.0, 1998, 100L ));
        movieList.add(new Movie("Godfather", 101.0, 1998, 200L ));
        movieList.add(new Movie("Lucy", 105.0, 2006, 560L ));
        movieList.add(new Movie("War", 100.0, 1998, 200L ));
        movieList.add(new Movie("War", 200.0, 2001, 300L ));
        //Sort by name, year, budget, numOfReview
        List<Movie> sortedMovieList = movieList.stream()
                .sorted(Comparator
                        .comparing((Movie movie) -> movie.getName())
                        .thenComparing(movie -> movie.getYear())
                        .thenComparing(movie -> movie.getBudgetInCr(), Comparator.reverseOrder())
                        .thenComparing(movie -> movie.getNumOfReviews())
                )
                .collect(Collectors.toList());

        for(Movie movie : sortedMovieList){
            System.out.printf("%s : %d : %f : %d \n", movie.getName(), movie.getYear(), movie.getBudgetInCr(), movie.getNumOfReviews());
        }

        List<Movie> sortedMovieList1 = movieList.stream()
                        .sorted(Comparator
                                .comparing(Movie::getName, Comparator.reverseOrder())
                                .thenComparing(Movie::getYear, Comparator.reverseOrder())
                        )
                                .collect(Collectors.toList());

        Map<String, Movie> movieMap = sortedMovieList.stream()
                .collect(Collectors.toMap(p -> p.getName(), p -> p, (existing, replacement) -> existing));
        Movie movie = movieMap.get("Godfather");
        System.out.printf("%s - %d : %f : %d \n", movie.getName(), movie.getYear(), movie.getBudgetInCr(), movie.getNumOfReviews());


        List<Integer> intList = Arrays.asList(1,2,3,4,4,6,1,7);
        List<Integer> uniqueIntList = intList.stream().distinct().collect(Collectors.toList());
        for(int i : uniqueIntList) {
            System.out.printf("%d ", i);
        }






    }



    @Getter
    static class Movie{
        String name;
        double budgetInCr;
        int year;
        long numOfReviews;

        public Movie(String name, double budgetInCr, int year, long numOfReviews) {
            this.name = name;
            this.budgetInCr = budgetInCr;
            this.year = year;
            this.numOfReviews = numOfReviews;
        }
    }
}
