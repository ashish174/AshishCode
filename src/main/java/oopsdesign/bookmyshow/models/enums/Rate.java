package oopsdesign.bookmyshow.models.enums;

public enum Rate {
    FIVE_STAR(5), FOUR_STAR(4), THREE_STAR(3), TWO_STAR(2), ONE_STAR(1);

    int rating;

    Rate(int i) {
        rating = i;
    }
}
