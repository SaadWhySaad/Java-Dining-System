package com.diningsystem.dining.system.service;


import com.diningsystem.dining.system.exception.EntityNotFoundException;
import com.diningsystem.dining.system.model.Restaurant;
import com.diningsystem.dining.system.repo.RestaurantRepository;
import com.diningsystem.dining.system.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Message<String> add(Restaurant restaurant){

        restaurant.setCreatedAt(LocalDate.now());
        restaurant.setIsActive(true);
        this.restaurantRepository.save(restaurant);
        Message message = new Message();
        message.setCode(HttpStatus.OK.value());
        message.setStatus(HttpStatus.OK.name());
        message.setMessage("Restaurant Added");
        message.setData("Restaurant Added to Database");
        return message;

    }

    public Message<List<Restaurant>> getAll(Integer pageNumber, Integer pageSize){

        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Restaurant> page = this.restaurantRepository.findAll(p);
        List<Restaurant> restaurants = page.getContent();

        if(!restaurants.isEmpty()){
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("All restaurants fetched.");
            message.setData(restaurants);
            return message;
        }

        throw new EntityNotFoundException("Restaurants not found.");

    }

}
