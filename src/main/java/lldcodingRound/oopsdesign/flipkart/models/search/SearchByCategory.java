package lldcodingRound.oopsdesign.flipkart.models.search;

import lldcodingRound.cartservice.model.Item;
import lldcodingRound.oopsdesign.flipkart.models.inventory.objects.ItemCategory;

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
