package com.masai.service.iRestaurantService;

import com.masai.exceptions.RestaurantException;
import com.masai.model.Restaurant;

import java.util.List;

public interface IRestaurantService {
    public Restaurant addRestaurant(Restaurant res) throws RestaurantException;
    public Restaurant updateRestaurant(Restaurant res,int key) throws RestaurantException;
    public String removeRestaurant(Restaurant res) throws RestaurantException;
    public Restaurant viewRestaurant(String key) throws RestaurantException;
    public List<Restaurant> viewNearByRestaurant(Restaurant res) throws RestaurantException;
    public List<Restaurant> viewRestaurantByItemNAme(Restaurant res) throws RestaurantException;
}
