package com.diningsystem.dining.system.controller;

import com.diningsystem.dining.system.model.RestaurantItem;
import com.diningsystem.dining.system.response.Message;
import com.diningsystem.dining.system.service.RestaurantItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantitem")
public class RestaurantItemController {

    @Autowired
    RestaurantItemService restaurantItemService;

    @PostMapping("/add")
    ResponseEntity<Message<String>> add(@RequestBody RestaurantItem restaurantItem){

        return ResponseEntity.ok(this.restaurantItemService.add(restaurantItem));

    }

    @GetMapping("/getall")
    ResponseEntity<Message<RestaurantItem>> getAll(@RequestParam(value = "pageNumber", defaultValue = "1", required = false)Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = "5", required = false)Integer pageSize){
        return ResponseEntity.ok(this.restaurantItemService.getAll(pageNumber,pageSize));
    }

}
