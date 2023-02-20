package com.codewitharjun.fullstackbackend.model;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
public class Order1 {

    @Id
    @GeneratedValue
    private Long id;
    
   /* @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JsonDeserialize(using = UserConverter.class)*/
    private Long userId;
    
    private Long price;
    private String product;
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
