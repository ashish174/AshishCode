package codingRound.oopsdesign.bookmyshow.models;

import codingRound.oopsdesign.bookmyshow.models.enums.Genre;
import codingRound.oopsdesign.bookmyshow.models.enums.Language;

import java.util.List;

public class Movie {
    private Long id;
    private String title;
    private String desc;
    private Genre genre;
    private List<Language> languages;
    private int rating;

    private List<Theater> theaters;
    private List<Reviews> reviews;

}
