package com.diningsystem.dining.system.controller;

import com.diningsystem.dining.system.model.User;
import com.diningsystem.dining.system.response.Message;
import com.diningsystem.dining.system.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    ResponseEntity<Message<String>> add(@RequestBody User user){
        return ResponseEntity.ok(this.userService.add(user));
    }

    @GetMapping("/getall")
    ResponseEntity<Message<List<User>>> getAll(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){

        return ResponseEntity.ok(this.userService.getAll(pageNumber, pageSize));

    }

    @GetMapping("/getById/{id}")
    ResponseEntity<Message<User>> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Message<User>> update(@RequestBody User user, @PathVariable Long id){
        return ResponseEntity.ok(this.userService.update(user, id));
    }

    @PutMapping("/setstatus/{id}")
    ResponseEntity<Message<String>> setActiveStatus(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.setActiveStatus(id));
    }
}
