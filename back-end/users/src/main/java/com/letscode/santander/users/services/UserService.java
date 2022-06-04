package com.letscode.santander.users.services;

import com.letscode.santander.users.domains.Cart;
import com.letscode.santander.users.domains.User;
import com.letscode.santander.users.gateways.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

    public List<User> getAll() { return userRepository.findAll();}

    public User getById(Integer userId){
        return userRepository.findById(userId).orElseThrow();
    }

    public User create(User user){
        try{
            userRepository.save(user);
            cartService.create(user.getCart());
        } catch (Exception e){
            System.out.println(e);
        }
        return user;
    }

    public Cart getUserCart(Integer userId){
        return getById(userId).getCart();
    }

    public User update(User user){
        User userToUpdate = getById(user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setAddress(user.getAddress());

        return create(userToUpdate);
    }

    public void delete(Integer userId){
        var deletable = getById(userId);
        userRepository.delete(deletable);
    }
}
