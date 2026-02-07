package algo.practice.a_common_code_functions.javalibs;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
public class SampleComparator {
    @Builder
    @Getter
    @ToString
    static class Movie {
        int year;
        String name;
        float rating;
        float earningsInCr;
    }

    public static void main(String[] args){
        List<Movie> movieList = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i <=10 ; i++) {
            movieList.add(
                    Movie.builder()
                            .year(random.nextInt(6)+2000)
                            .name("MyMovie"+random.nextInt(100))
                            .rating((random.nextFloat()*51)/10.0f)
                            .earningsInCr(Math.round(random.nextFloat()*5000F)/1.00f)
                            .build()
            );
        }

        //log.info("Unsorted: {}",movieList);
        log.info("Unsorted List : ");
        movieList.stream().forEach(movie -> log.info("{}", movie));
        movieList.sort((o1, o2) -> (int) (o1.year - o2.year));
        //log.info("Sorted: {}",movieList);
        log.info("Sorted List : ");
        movieList.stream().forEach(movie -> log.info("{}", movie));





    }
}
