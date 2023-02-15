package com.codewitharjun.fullstackbackend.controller;

import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.Order1;
import com.codewitharjun.fullstackbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order")
    Order1 newOrder(@RequestBody Order1 newOrder) {
    	System.out.println(newOrder.toString());
        return orderRepository.save(newOrder);
    }

    @GetMapping("/orders")
    List<Order1> getAllOrders() {
    	System.out.println("getAllOrders");
        return orderRepository.findAll();
    }

    @GetMapping("/order/{userId}")
    Order1 getOrderById(@PathVariable Long userId) {
        return orderRepository.findFirstByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
    
    @GetMapping("/orders/{userId}")
    List<Order1> getOrdersById(@PathVariable Long userId) {
        return orderRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @PutMapping("/order/{id}")
    Order1 updateOrder(@RequestBody Order1 newOrder, @PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                	order.setUserId(newOrder.getUserId());
                	order.setPrice(newOrder.getPrice());
                    return orderRepository.save(order);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/order/{id}")
    String deleteOrder(@PathVariable Long id){
        if(!orderRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        orderRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }



}
