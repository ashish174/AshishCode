package algo.practice.a_common_code_functions;

import algo.practice.a_common_code_functions.sampleclasses.Product;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Slf4j
public class ComparatorSample {

    List<Product> productList;



    public void sampleFunction(){

        List<Product> products = getProductList();
        // products.sort(Comparator.naturalOrder());    // ascending (via Comparable)
        // products.sort(Comparator.reverseOrder());

        // Comparator for Asc
        Comparator<Product> priceAsc = Comparator.comparing(Product::getPrice);
        // Comparator for Desc
        Comparator<Product> priceDesc = Comparator.comparing(Product::getPrice).reversed(); // Descending
        Collections.sort(products, priceDesc);




        productList = getProductList();
        log.info("Initial List: {}", productList);

        productList.sort((o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));
        log.info("Price Sorted List: {}", productList);

        productList.sort((o1,o2) -> o1.getName().compareTo(o2.getName()));
        log.info("Name Sorted List: {}", productList);

        productList.sort((o1,o2) -> {
            int compareTo = (int) (o1.getPrice()-o2.getPrice());
            return compareTo != 0 ? compareTo : o1.getName().compareTo(o2.getName());
        });

        // For multiple comparisons, we can also chain comparators using Comparator.thenComparing()
        Comparator<Product> productComparator = Comparator.comparing(Product::getCategory)
                .thenComparing(Product::getName)
                .thenComparing(Product::getStockQuantity);

        List<Product> productListSortByComparator = new ArrayList<>(productList);
        productListSortByComparator.sort(productComparator);
        log.info("Comparator Sorted List: {}", productListSortByComparator);

        // For multiple comparisons
        Comparator<Product> productComparatorAscAndDesc = Comparator.comparing(Product::getCategory)
                .thenComparing(Product::getName)
                .thenComparing(Comparator.comparing(Product::getStockQuantity).reversed());
        List<Product> productListSortByComparatorAscDesc = new ArrayList<>(productList);

        log.info("Comparator Sorted List by Asc Desc: {}", productListSortByComparatorAscDesc);




    }

    List<Product> getProductList() {
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
        return productList;
    }

    public static void main(String[] args) {





  }

}
