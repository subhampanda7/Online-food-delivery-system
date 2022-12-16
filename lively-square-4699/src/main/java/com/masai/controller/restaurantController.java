package com.masai.controller;

import com.masai.exceptions.RestaurantException;
import com.masai.model.Restaurant;
import com.masai.service.iRestaurantService.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class restaurantController {
    @Autowired
    private IRestaurantService iRestaurantService;

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> addRestaurantHandle(@RequestBody Restaurant restaurant) throws RestaurantException{
        Restaurant addedRestaurant=iRestaurantService.addRestaurant(restaurant);
        return new ResponseEntity<Restaurant>(addedRestaurant, HttpStatus.CREATED);
    }


}
