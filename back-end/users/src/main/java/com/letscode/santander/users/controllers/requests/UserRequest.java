package com.letscode.santander.users.controllers.requests;

import com.letscode.santander.users.domains.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String address;
    private List<Integer> orders = new ArrayList<>();


    public User toDomain(){
        User user = new User();
        user.setName(this.name);
        user.setAddress(this.address);
        user.setOrders(this.orders);
        user.getCart().setOwner(user);

        return user;
    }
}
