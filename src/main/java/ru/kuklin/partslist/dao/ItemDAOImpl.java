package ru.kuklin.partslist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.kuklin.partslist.model.Item;

import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO {
    private static final Logger logger = LoggerFactory.getLogger(ItemDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addItem(Item item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(item);
        logger.info("Successfully writen, Item details = " + item);
    }

    @Override
    public void updateItem(Item item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(item);
        logger.info("Successfully update, Item details = " + item);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> listItem(String query) {
//        if(sort.equals("req"))sort = "req desc";
        Session session = this.sessionFactory.getCurrentSession();
//        List<Item> listItem = session.createQuery("from Item order by "+sort).list();
        List<Item> listItem = session.createQuery(query).list();
//        for(Item item:listItem){
//            logger.info("Items list:" + item);
//        }
        return listItem;
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public List<Item> getItemByName(String search) {
//        Session session = this.sessionFactory.getCurrentSession();
//        List<Item> itemList = session.createQuery("from Item where lower(name) like lower('%"+ search+"%')").list();
//        return itemList;
//    }

    @Override
    public Item getItemById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Item item = session.load(Item.class, new Integer(id));
        logger.info("Item loaded: " + item);
        return item;
    }

    @Override
    public void removeItem(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Item item = session.load(Item.class, new Integer(id));
        if(item != null){
            session.delete(item);
            logger.info("Removing items: " + item);
        }else {
            logger.info("Removing items error, items not found");
        }
    }

    @Override
    public int canCollect() {
        List<Item> list = listItem("name");
        int result = Integer.MAX_VALUE;
        for(Item i:list){
            if(i.getReq() && i.getQty()<result)result=i.getQty();
        }
        return result;
    }
}
