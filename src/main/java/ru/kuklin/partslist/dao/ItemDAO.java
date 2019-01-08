package ru.kuklin.partslist.dao;

import java.util.List;
import ru.kuklin.partslist.model.Item;

public interface ItemDAO {
    void addItem(Item item);
    void updateItem(Item item);
    List<Item> listItem(String sort);
    Item getItemById(int id);
    void removeItem(int id);
    int canCollect();
}
