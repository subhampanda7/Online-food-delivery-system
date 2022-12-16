package com.masai.service.iItemService;

import com.masai.exceptions.ItemException;
import com.masai.model.Category;
import com.masai.model.Item;
import com.masai.model.Restaurant;

import java.util.List;

public interface IItemService {
    public Item addItem(Item item) throws ItemException;

    public Item updateItem(Item item)throws ItemException;

    public Item viewItem(Item item)throws ItemException;

    public String removeItem(Item item)throws ItemException;

    public List<Item> viewAllItemsByCategory(Category cat)throws ItemException;

    public List<Item> viewAllItems(Restaurant res)throws ItemException;

    public List<Item> viewAllItemsByName(String name)throws ItemException;
}
