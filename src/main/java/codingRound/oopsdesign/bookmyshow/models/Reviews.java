package codingRound.oopsdesign.bookmyshow.models;

import codingRound.oopsdesign.bookmyshow.models.enums.Rate;

public class Reviews {
    private Long id;
    private Long movieId;
    private Long customerId;
    private Rate rating;
    private String reviewComments;
}
