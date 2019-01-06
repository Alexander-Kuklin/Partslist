package ru.kuklin.partslist.dao;

import java.util.List;
import ru.kuklin.partslist.model.Item;

public interface ItemDAO {
    public void addItem(Item item);
    public void updateItem(Item item);
    public List<Item> listItem(String sort);
//    public List<Item> getItemByName(String search);
    public Item getItemById(int id);
    public void removeItem(int id);
    public int canCollect();
}
