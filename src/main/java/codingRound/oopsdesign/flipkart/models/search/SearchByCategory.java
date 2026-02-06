package codingRound.oopsdesign.flipkart.models.search;

import codingRound.cartservice.model.Item;
import codingRound.oopsdesign.flipkart.models.inventory.objects.ItemCategory;

import java.util.List;

public class SearchByCategory extends Search{
    ItemCategory category;
    @Override
    public List<Item> search() {
        return searchByCategory(category);
    }

    public List<Item> searchByCategory(ItemCategory category){
        return SearchCatalogue.itemsByCategory.get(category);
    }
}
