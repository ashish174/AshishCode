package algo.practice.a_common_code_functions.sampleclasses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
public class Product {
    private String name;
    private double price;
    private Category category;
    private int stockQuantity;

    public enum Category {
        BOOKS,
        ELECTRONICS,
        FURNITURE,
        TOYS,
        SPORTS
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, category);
    }
}




