package com.dab.household.dao.jpa;

import com.dab.household.dao.ItemDao;
import com.dab.household.entity.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JpaItemDao implements ItemDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Item addItem(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public List<Item> findItemsByTitle(String title) {
        List<Item> items;

        try {
            items = em.createQuery("SELECT i FROM Item i WHERE i.title LIKE :title")
                    .setParameter("title", title + "%").getResultList();
        } catch (Exception e) {
            items = new ArrayList<>();
        }

        return items;
    }

    @Override
    public Item findItemById(Long id) {
        return em.find(Item.class, id);
    }
}
