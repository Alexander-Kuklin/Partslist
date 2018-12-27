package ru.kuklin.partslist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuklin.partslist.dao.ItemDAO;
import ru.kuklin.partslist.model.Item;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private ItemDAO itemDAO;

    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    @Transactional
    public void addItem(Item item) {
        this.itemDAO.addItem(item);
    }

    @Override
    @Transactional
    public void updateItem(Item item) {
        this.itemDAO.updateItem(item);
    }

    @Override
    @Transactional
    public List<Item> listItem() {
        return this.itemDAO.listItem();
    }

    @Override
    @Transactional
    public Item getItemById(int id) {
        return this.itemDAO.getItemById(id);
    }

    @Override
    @Transactional
    public void removeItem(int id) {
        this.itemDAO.removeItem(id);
    }
}
