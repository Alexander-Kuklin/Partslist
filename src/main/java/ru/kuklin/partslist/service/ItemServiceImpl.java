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
    public List<Item> listItem(String sort) {
        if(sort!=null){
            if(sort.equals("req"))sort = "req desc";
        }
        return this.itemDAO.listItem("from Item order by "+sort);
    }

    @Override
    @Transactional
    public List<Item> getItemByName(String search, String sort) {
        StringBuilder query = new StringBuilder();
        query.append("from Item");
        query.append(" where lower(name) like lower('%" + search + "%')");
        if(sort!=null){
            if(sort.equals("req"))sort = "req desc";
            query.append(" order by "+sort);
        }
        return this.itemDAO.listItem(query.toString());
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

    @Override
    @Transactional
    public int canCollect(){
        return this.itemDAO.canCollect();
    }


}
