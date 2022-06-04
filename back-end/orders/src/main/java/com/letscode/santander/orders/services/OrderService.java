package com.letscode.santander.orders.services;

import com.letscode.santander.orders.controllers.responses.external.CartResponse;
import com.letscode.santander.orders.domains.Order;
import com.letscode.santander.orders.domains.enums.Status;
import com.letscode.santander.orders.gateways.OrderRepository;
import com.letscode.santander.orders.gateways.clients.CartClient;
import com.letscode.santander.orders.gateways.clients.ReviewClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderService {

    @Autowired
    CartClient cartClient;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ReviewClient reviewClient;

    public static final List<CartResponse> availableCarts = new ArrayList<>();

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

    public Order create(Integer cartId){
        Order order = new Order();
        checkCarts(false);
        try{
            for (CartResponse cart : availableCarts) {
                if (cart.getId() == cartId){
                    order.setProducts(cart.getProducts());
                    order.setTotal(cart.getTotalCost());
                    order.setUserId(cartId);
                    break;
                }
            }
            orderRepository.save(order);
        } catch (Exception e){
            log.info("SAVING ORDER ERROR: " + e.toString());
        }
        clearCartAfterOrderSet(cartId);
        alertAvailableReview();
        return order;
    }

    private void clearCartAfterOrderSet(Integer cartId){
        cartClient.clearCart(cartId);
    }

    private void alertAvailableReview(){
        reviewClient.fetchOrders();
    }

    public void checkCarts(boolean shouldFetch){
        if (availableCarts.isEmpty() || shouldFetch){
            availableCarts.clear();
            availableCarts.addAll(cartClient.getCartsFromDb());
        }
    }
}
