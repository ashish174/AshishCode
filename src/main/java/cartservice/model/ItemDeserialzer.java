package cartservice.model;

import cartservice.constants.ItemType;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ItemDeserialzer {

  private static final Gson GSON = new Gson();

  public static void main(String[] args) {
    ItemDeserialzer deserialzer = new ItemDeserialzer();
    Map<String, Object> params = new HashMap<>();
    params.put("itemType", "BOOK");
    deserialzer.createItem(params);
  }

  public void createItem(Map<String, Object> params) {
    String typeStr = (String)params.get("itemType");
    ItemType itemType = ItemType.valueOf(typeStr);
    Item item = GSON.fromJson(GSON.toJsonTree(params), getClass(itemType));
  }

  public Class<? extends Item> getClass(ItemType type) {
    switch (type) {
      case GROCERY:
        return Grocery.class;
      case MEDICINE:
        return Medicine.class;
      case BOOK:
        return Book.class;
    }
    throw new RuntimeException("Unsupported itemType: " + type);
  }

}
