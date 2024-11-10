package com.diningsystem.dining.system.controller;


import com.diningsystem.dining.system.model.Restaurant;
import com.diningsystem.dining.system.response.Message;
import com.diningsystem.dining.system.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/add")
    ResponseEntity<Message<String>> add(@RequestBody Restaurant restaurant){

        return ResponseEntity.ok(this.restaurantService.add(restaurant));

    }

    @GetMapping("/getall")
    ResponseEntity<Message<List<Restaurant>>> getAll(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){

        return ResponseEntity.ok(this.restaurantService.getAll(pageNumber, pageSize));

    }

    @GetMapping("/getbyid/{id}")
    ResponseEntity<Message<Restaurant>> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.restaurantService.getById(id));
    }

}
