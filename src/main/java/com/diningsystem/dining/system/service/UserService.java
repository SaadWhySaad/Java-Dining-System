package com.diningsystem.dining.system.service;

import com.diningsystem.dining.system.exception.BadRequestException;
import com.diningsystem.dining.system.exception.EntityNotFoundException;
import com.diningsystem.dining.system.exception.InternalServerErrorException;
import com.diningsystem.dining.system.model.User;
import com.diningsystem.dining.system.repo.UserRepository;
import com.diningsystem.dining.system.response.Message;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Message<String> add(User user){
        user.setCreatedAt(LocalDate.now());
        user.setIsActive(true);
        this.userRepository.save(user);
        Message message = new Message();
        message.setCode(HttpStatus.OK.value());
        message.setStatus(HttpStatus.OK.name());
        message.setMessage("User stored in database.");
        message.setData("User stored in database.");
        return message;
    }


    public Message<List<User>> getAll(Integer pageNumber, Integer pageSize){
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<User> page = this.userRepository.findAll(p);
        List<User> users = page.getContent();

        if(!users.isEmpty()){
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("Users Fetched.");
            message.setData(users);
            return message;
        }

        throw new EntityNotFoundException("Data Not Available.");
    }

    public Message<User> getById(Long id){
        User user = this.userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found."));
        if(user.getIsActive()) {
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("User found.");
            message.setData(user);
            return message;
        }
        throw new InternalServerErrorException("Server Error");

    }

    public Message<User> update(User user, Long id){
        User tempUser = this.userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found."));
        if(tempUser.getIsActive()){
            User u = tempUser;
            u.setUserName(user.getUserName());
            u.setPassword(user.getPassword());
            u.setEmailAddress(user.getEmailAddress());
            u.setHomeAddress(user.getHomeAddress());
            u.setPhoneNumber(user.getPhoneNumber());
            u.setUpdatedAt(LocalDateTime.now());
            this.userRepository.save(u);
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("User Updated.");
            message.setData(tempUser);
            return message;
        }
        throw new BadRequestException("An error occurred.");
    }

    public Message<String> setActiveStatus(Long id){
        User user = this.userRepository.findByIdAndIsActive(id, true);
        if(Objects.nonNull(user)){
            user.setIsActive(false);
            this.userRepository.save(user);
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("User Status Changed.");
            message.setData("User Status Changed.");
            return message;
        }else{
            throw new EntityNotFoundException("User not found.");
        }
    }

}
