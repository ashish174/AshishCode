package codingRound.oopsdesign.flipkart.models.search;

import codingRound.cartservice.model.Item;
import codingRound.oopsdesign.flipkart.models.inventory.objects.ItemCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCatalogue {
    public static Map<String, List<Item>> itemsByName;
    public static Map<ItemCategory, List<Item>> itemsByCategory;

    static {
        itemsByName = new HashMap<>();
        itemsByCategory = new HashMap<>();
    }

}
