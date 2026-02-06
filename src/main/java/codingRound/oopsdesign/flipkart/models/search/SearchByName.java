package codingRound.oopsdesign.flipkart.models.search;

import codingRound.cartservice.model.Item;

import java.util.List;

public class SearchByName extends Search{
    private String name;
    @Override
    public List<Item> search() {
        return searchByName(name);
    }

    public List<Item> searchByName(String name){
        return SearchCatalogue.itemsByName.get(name);
    }
}
