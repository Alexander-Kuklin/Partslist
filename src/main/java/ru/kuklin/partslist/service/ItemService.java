package ru.kuklin.partslist.service;

import ru.kuklin.partslist.model.Item;

import java.util.List;

public interface ItemService {
    public void addItem(Item item);
    public void updateItem(Item item);
    public List<Item> listItem(String sort);
    public List<Item> getItemByName(String search, String sort);
    public Item getItemById(int id);
    public void removeItem(int id);
    public int canCollect();
}
