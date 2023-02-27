package com.codewitharjun.fullstackbackend.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Order1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* @ManyToOne
     @JoinColumn(name = "userId", referencedColumnName = "id")
     @OnDelete(action=OnDeleteAction.CASCADE)
     @JsonDeserialize(using = UserConverter.class)*/
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private Long price;
    private String product;
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /*
    public String toString() {
	      return name+'-'+username+'-'+email;
	    }*/
}
