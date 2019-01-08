package ru.kuklin.partslist.service;

import ru.kuklin.partslist.model.Item;

import java.util.List;

public interface ItemService {
    void addItem(Item item);
    void updateItem(Item item);
    List<Item> listItem(String sort);
    List<Item> getItemByName(String search, String sort);
    List<Item> getItemByName(String search, String sort, Boolean filter);
    Item getItemById(int id);
    void removeItem(int id);
    int canCollect();
}
