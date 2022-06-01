package com.letscode.santander.orders.services;

import com.letscode.santander.orders.domains.Order;
import com.letscode.santander.orders.domains.enums.Status;
import com.letscode.santander.orders.gateways.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAll() {return orderRepository.findAll();}

    public Order create(Order order){
        try {
            orderRepository.save(order);
        } catch (Exception e){
            System.out.println(e);
        }
        return order;
    }

    public Order getById(Integer orderId) {return orderRepository.findById(orderId).orElseThrow();}

    public Order update(Integer orderId, boolean continueOrCancel) {
        Map<Status, Status> process = Map.of(Status.PROCESSING, Status.APPROVED,
                Status.APPROVED, Status.SHIPPED,
                Status.SHIPPED, Status.DELIVERED,
                Status.CANCELLED, Status.CANCELLED);

        Order orderToUpdate = getById(orderId);
        if (continueOrCancel) {
            orderToUpdate.setStatus(process.get(orderToUpdate.getStatus()));
        } else {
            orderToUpdate.setStatus(Status.CANCELLED);
        }
        return orderRepository.save(orderToUpdate);
    }


}
