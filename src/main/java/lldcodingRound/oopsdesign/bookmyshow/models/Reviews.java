package lldcodingRound.oopsdesign.bookmyshow.models;

import lldcodingRound.oopsdesign.bookmyshow.models.enums.Rate;

public class Reviews {
    private Long id;
    private Long movieId;
    private Long customerId;
    private Rate rating;
    private String reviewComments;
}
