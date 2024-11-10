package com.diningsystem.dining.system.service;

import com.diningsystem.dining.system.exception.EntityNotFoundException;
import com.diningsystem.dining.system.model.RestaurantItem;
import com.diningsystem.dining.system.repo.RestaurantItemRepository;
import com.diningsystem.dining.system.repo.RestaurantRepository;
import com.diningsystem.dining.system.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantItemService {

    @Autowired
    RestaurantItemRepository restaurantItemRepository;

    public Message<String> add(RestaurantItem restaurantItem){

        this.restaurantItemRepository.save(restaurantItem);
        Message message = new Message();
        message.setCode(HttpStatus.OK.value());
        message.setStatus(HttpStatus.OK.name());
        message.setMessage("Restaurant Item has been saved for restaurant: " + restaurantItem.getRestaurant().getName());
        message.setData("Restaurant Item has been saved for restaurant: " + restaurantItem.getRestaurant().getName());
        return message;

    }

    public Message<RestaurantItem> getAll(Integer pageNumber, Integer pageSize){
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<RestaurantItem> page = this.restaurantItemRepository.findAll(p);
        List<RestaurantItem> restaurantItems = page.getContent();

        if(!restaurantItems.isEmpty()){
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("Items fetched for their respective restaurants.");
            message.setData(restaurantItems);
            return message;
        }

        throw new EntityNotFoundException("No items found.");
    }

}
