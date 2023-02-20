package com.codewitharjun.fullstackbackend.controller;

import com.codewitharjun.fullstackbackend.exception.OrderNotFoundException;
import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.Order1;
import com.codewitharjun.fullstackbackend.model.User;
import com.codewitharjun.fullstackbackend.repository.OrderRepository;
import com.codewitharjun.fullstackbackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class OrderController {

	@Autowired
    private OrderRepository orderRepository;

    
    /*@Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;}*/
    /*@PostMapping("/order")
    Order1 newOrder(@RequestBody Order1 newOrder) {
    	System.out.println(newOrder.toString());
        return orderRepository.save(newOrder);
    }*/
    
    @PostMapping("/order")
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
    

    @PutMapping("/order/{id}")
    Order1 updateOrder(@RequestBody Order1 newOrder, @PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                	order.setUserId(newOrder.getUserId());
                	order.setPrice(newOrder.getPrice());
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @DeleteMapping("/order/{id}")
    String deleteOrder(@PathVariable Long id){
        if(!orderRepository.existsById(id)){
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }



}
