package com.masai.service.iItemService;

import com.masai.exceptions.ItemException;
import com.masai.model.Category;
import com.masai.model.Item;
import com.masai.model.Restaurant;

import java.util.List;

public class IItemServiceImpl implements IItemService {

    @Override
    public Item addItem(Item item) throws ItemException {
        return null;
    }

    @Override
    public Item updateItem(Item item) throws ItemException {
        return null;
    }

    @Override
    public Item viewItem(Item item) throws ItemException {
        return null;
    }

    @Override
    public String removeItem(Item item) throws ItemException {
        return null;
    }

    @Override
    public List<Item> viewAllItemsByCategory(Category cat) throws ItemException {
        return null;
    }

    @Override
    public List<Item> viewAllItems(Restaurant res) throws ItemException {
        return null;
    }

    @Override
    public List<Item> viewAllItemsByName(String name) throws ItemException {
        return null;
    }
}
