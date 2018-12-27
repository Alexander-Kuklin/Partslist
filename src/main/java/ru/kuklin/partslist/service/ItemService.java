package ru.kuklin.partslist.service;

import ru.kuklin.partslist.model.Item;

import java.util.List;

public interface ItemService {
    public void addItem(Item item);
    public void updateItem(Item item);
    public List<Item> listItem();
    public Item getItemById(int id);
    public void removeItem(int id);
}
