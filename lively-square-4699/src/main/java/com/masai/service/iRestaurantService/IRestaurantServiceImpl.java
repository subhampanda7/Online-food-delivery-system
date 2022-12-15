package com.masai.service.iRestaurantService;

import com.masai.exceptions.RestaurantException;
import com.masai.model.Restaurant;
import com.masai.repository.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRestaurantServiceImpl implements IRestaurantService{

    @Override
    public Restaurant addRestaurant(Restaurant res) throws RestaurantException {

        return null;
    }

    @Override
    public Restaurant updateRestaurant(Restaurant res) throws RestaurantException {
        return null;
    }

    @Override
    public String removeRestaurant(Restaurant res) throws RestaurantException {
        return null;
    }

    @Override
    public Restaurant viewRestaurant(Restaurant res) throws RestaurantException {
        return null;
    }

    @Override
    public List<Restaurant> viewNearByRestaurant(Restaurant res) throws RestaurantException {
        return null;
    }

    @Override
    public List<Restaurant> viewRestaurantByItemNAme(Restaurant res) throws RestaurantException {
        return null;
    }
}
