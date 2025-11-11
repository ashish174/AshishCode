package algo.practice.a_common_code_functions;

import algo.practice.a_common_code_functions.sampleclasses.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class ComparatorSample {

  // products.sort(Comparator.naturalOrder());    // ascending (via Comparable)
  // products.sort(Comparator.reverseOrder());

  // Comparator<Product> priceAsc = Comparator.comparing(Product::getPrice);        // Ascending
  // Comparator<Product> priceDesc = Comparator.comparing(Product::getPrice).reversed(); // Descending

  public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i < 10; i++) {
            productList.add(Product.builder()
                            .name("Product"+i)
                            .price(random.nextDouble(200.0))
                            .category(Product.Category.values()[random.nextInt(5)])
                            .stockQuantity(random.nextInt(500))
                    .build());
        }

        log.info("Initial List: {}", productList);


    }

}
