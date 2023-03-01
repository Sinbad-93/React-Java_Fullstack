package com.codewitharjun.fullstackbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewitharjun.fullstackbackend.exception.OrderNotFoundException;
import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.Order1;
import com.codewitharjun.fullstackbackend.repository.OrderRepository;
import com.codewitharjun.fullstackbackend.repository.OrderPageableRepository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;


@RestController
@CrossOrigin("http://localhost:3000")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderPageableRepository orderPageableRepository;
    
    

    /*@Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;}*/
    /*@PostMapping("/order")
    Order1 newOrder(@RequestBody Order1 newOrder) {
    	System.out.println(newOrder.toString());
        return orderRepository.save(newOrder);
    }*/

    @PostMapping("/order") // create order with id = null
    public Order1 newOrder(@RequestBody Order1 order) {
        // 4 lignes servent essentiellement à vérifier si l'utilisateur existe sinon renvoie une exception
        //On doit convertir l'userId de type User en Long.
    	/*Long userId = order.getUserId().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        order.setUserId(user);*/

        return orderRepository.save(order);
    }

    @GetMapping("/orders")
    List<Order1> getAllOrders() {
        System.out.println("getAllOrders");
        return orderRepository.findAll();
    }

    @GetMapping("/orders_limited/{userId}")
    List<Order1> getOrderById(@PathVariable Long userId) {
        return orderRepository.findFirst10ByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
    

    @GetMapping("/orders/{userId}")
    List<Order1> getOrdersById(@PathVariable Long userId) {
        return orderRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
    
    @GetMapping("/orders_pageable/{userId}/{pStart}/{pEnd}")
    List<Order1> getOrdersPageableById(@PathVariable Long userId, @PathVariable int pStart,@PathVariable int pEnd) {
    	System.out.println(pStart + "/" + pEnd);
    	Pageable towByTow = PageRequest.of(pStart, pEnd);
        return orderPageableRepository.findByUserId(userId, towByTow)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    /* Lorsque tu envoies l'ID d'un utilisateur depuis le front-end,
     * Spring va automatiquement convertir cette valeur en un objet User correspondant à cet ID.
     * C'est ce qui se passe grâce à l'annotation @PathVariable dans le contrôleur.*/
   /*@GetMapping("/orders-with-email/{userId}")
    List<Order1> getAllOrdersWithEmail(@PathVariable User userId) {
        return orderRepository.findAllWithUserEmail(userId)
        		.orElseThrow(() -> new OrderNotFoundException(userId));
    }*/
    
    /* Autre manière de faire sans utiliser la classe User mais seulement l'id_user
     * -> la méthode et la query du repo est modifié en conséquences 
     *@GetMapping("/orders-with-email/{userId}")
    List<Order1> getAllOrdersWithEmail(@PathVariable Long userId) {
        return orderRepository.findAllWithUserEmail(userId)
        		.orElseThrow(() -> new UserNotFoundException(userId));
    }*/

    @PutMapping("/order") // update order with id in order object
    Order1 updateOrder(@RequestBody Order1 order) {
        if (!orderRepository.existsById(order.getId())) {
            throw new OrderNotFoundException(order.getId());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/order/{id}")
    String deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
        return "User with id " + id + " has been deleted success.";
    }
}
